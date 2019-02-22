/*
 * Copyright 2018. nekocode (nekocode.cn@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stayli.plugins.transform

import com.android.annotations.NonNull
import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import com.google.common.collect.ImmutableSet
import com.google.common.io.ByteStreams
import groovy.json.JsonSlurper
import javassist.ClassPool
import org.gradle.api.Project

import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

import static UpdateFiltersFileTask.FILTERS_FILE_NAME

/**
 * @author nekocode (nekocode.cn@gmail.com)
 */
class JarFilterTransform extends Transform {
    private final Project project
    private final File filtersFile

    String androidPath;
    private static final ClassPool pool = ClassPool.getDefault()

    JarFilterTransform(Project project) {
        this.project = project
        androidPath = project.android.bootClasspath[0].toString()
        filtersFile = new File(project.getBuildDir(), FILTERS_FILE_NAME)
    }

    @NonNull
    @Override
    String getName() {
        return "jarFilter"
    }

    @NonNull
    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
//        return Sets.immutableEnumSet(QualifiedContent.Scope.EXTERNAL_LIBRARIES)
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @NonNull
    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @NonNull
    @Override
    Collection<SecondaryFile> getSecondaryFiles() {
        return ImmutableSet.of(
                SecondaryFile.nonIncremental(project.files(filtersFile))
        )
    }

    @Override
    boolean isIncremental() {
        return true
    }

    @Override
    void transform(TransformInvocation invocation)
            throws TransformException, InterruptedException, IOException {

        final TransformOutputProvider outputProvider = invocation.getOutputProvider()
        assert outputProvider != null

        final List list = new JsonSlurper().parse(filtersFile.readBytes())
        final JarFilters filters = new JarFilters(
                list.stream().map { JarFilter.fromMap(it) }.collect().toSet()
        )


        if (!invocation.isIncremental()) {
            outputProvider.deleteAll()
        }


        invocation.inputs.each { input ->

            input.directoryInputs.each { dirInput ->
                if (invocation.isIncremental()) {
                    Map<File, Status> dir = dirInput.changedFiles

                    dir.each {
                        map ->
                            File file = map.key
                            System.err.println(file.getAbsolutePath())
                            Status status = map.value
                            switch (status) {
                                case Status.NOTCHANGED:
                                    break
                                case Status.ADDED:
                                case Status.CHANGED:
                                    copyAndFilterDirectory(outputProvider, dirInput)
                                    break
                                case Status.REMOVED:

                                    break
                            }
                    }
                } else {

                    copyAndFilterDirectory(outputProvider, dirInput)
                }
            }


            input.jarInputs.each { jarInput ->
                if (invocation.isIncremental()) {
                    switch (jarInput.status) {
                        case Status.NOTCHANGED:
                            break
                        case Status.ADDED:
                        case Status.CHANGED:
                            copyAndFilterJar(outputProvider, jarInput, filters)
                            break
                        case Status.REMOVED:
                            final File outJarFile = outputProvider.getContentLocation(
                                    jarInput.name,
                                    jarInput.contentTypes,
                                    jarInput.scopes,
                                    Format.JAR
                            )
                            FileUtils.deleteIfExists(outJarFile)
                            break
                    }

                } else {
                    copyAndFilterJar(outputProvider, jarInput, filters)
                }
            }
        }
    }


    private void copyAndFilterDirectory(TransformOutputProvider outputProvider,
                                        DirectoryInput directoryInput) {
        // 获取output目录
        def dest = outputProvider.getContentLocation(directoryInput.name,
                directoryInput.contentTypes, directoryInput.scopes,
                Format.DIRECTORY)

        File file = directoryInput.file
        System.err.println(file.getAbsolutePath())

        String path = file.getAbsolutePath()
        pool.appendClassPath(path)
        pool.appendClassPath(androidPath)
        //引入android.os.Bundle包，因为onCreate方法参数有Bundle
        pool.importPackage("android.os.Bundle")
        ClassInject.injectDir(path, 'com/stayli/app/act/SplashActivity',pool)

        // 将input的目录复制到output指定目录
        FileUtils.copyDirectory(file, dest)
    }

    private static void copyAndFilterJar(TransformOutputProvider outputProvider,
                                         JarInput jarInput, JarFilters filters) {

        final File outJarFile = outputProvider.getContentLocation(
                jarInput.name,
                jarInput.contentTypes,
                jarInput.scopes,
                Format.JAR
        )

        final JarFilters.FileFilter filter = filters.getFileFilter(jarInput.name)

        if (filter == null) {
            // No matched jar filters
            FileUtils.copyFile(jarInput.file, outJarFile)
            return
        }

        new ZipInputStream(new FileInputStream(jarInput.file)).withCloseable { zis ->
            new ZipOutputStream(new FileOutputStream(outJarFile)).withCloseable { zos ->

                ZipEntry entry
                while ((entry = zis.getNextEntry()) != null) {
                    if (!filter.test(entry.getName())) {
                        // Skip this file
                        continue
                    }

                    zos.putNextEntry(entry)
                    ByteStreams.copy(zis, zos)
                    zos.closeEntry()
                    zis.closeEntry()
                }
            }
        }
    }
}