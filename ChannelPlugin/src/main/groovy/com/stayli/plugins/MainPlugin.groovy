package com.stayli.plugins

import com.android.build.gradle.AppExtension
import com.stayli.plugins.channel.ChannelExtension
import com.stayli.plugins.transform.JarFilter
import com.stayli.plugins.transform.JarFilterTransform
import com.stayli.plugins.transform.JarFilters
import com.stayli.plugins.transform.UpdateFiltersFileTask
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer

public class MainPlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {

        project.repositories {
            mavenLocal()
        }

//
        PluginContainer plugins = project.plugins;
        if (plugins.hasPlugin("com.android.application")) {
            //监听编译时间
            project.getGradle().addListener(new TimeTrace())
        }
        channelPlugin(project)
        jarFilterPlugin(project)

    }


    void jarFilterPlugin(Project project) {
        project.extensions.add('jarFilters', project.container(JarFilter))
        project.getTasks()['preBuild']
                .dependsOn(project.tasks.create('updateJarFiltersFile', UpdateFiltersFileTask))

        def android = project.getExtensions().getByName("android")

        if (android != null && android instanceof AppExtension) {
            ((AppExtension) android).registerTransform(new JarFilterTransform(project))

        } else {
            throw new RuntimeException("The JarFilterPlugin can only be used in android application module.")
        }
    }

    void channelPlugin(Project project) {
        //创建渠道扩展
        project.extensions.create("channel", ChannelExtension)

        // 获取 channel 配置
        ChannelExtension type = project.extensions.getByType(ChannelExtension);

        // 扫描build文件之后回调
        project.afterEvaluate(new Action<Project>() {
            @Override
            void execute(Project pro) {
                pro.getLogger().quiet("plugin get ...")
                project.getLogger().quiet(type.includes.toString());

                JarFilters container = project.extensions.getByName('jarFilters')

                pro.getLogger().quiet("plugin get jar...")
                container.mathcers.each {
                    pair ->

                        JarFilters.FileFilter filter = pair.right
                        pro.getLogger().quiet(pair.left.toString()
                                + ":" + filter.jarFilter.name
                        )
                        filter.includes.each { pattern ->
                            pro.getLogger().quiet(pattern.toString())
                        }

                        filter.excludes.each { pattern ->
                            pro.getLogger().quiet(pattern.toString())
                        }


                }


            }
        });
    }
}
