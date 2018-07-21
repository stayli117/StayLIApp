package com.stayli.channelplugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.PluginContainer;

public class ChannelPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {

        project.getRepositories().mavenLocal();
//        project.getDependencies().add("org.aspectj:aspectjrt:1.8.9", new Object());

        PluginContainer plugins = project.getPlugins();
        if (plugins.hasPlugin("com.android.application")) {
            //build time trace
            project.getGradle().addListener(new TimeTrace());
        }


        //创建扩展
        final ExtensionContainer extensions = project.getExtensions();
        extensions.create("channel", ChannelExtension.class);
        // 扫描build文件之后回调
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {

                // 获取 channel 配置
                ChannelExtension type = extensions.getByType(ChannelExtension.class);
                project.getLogger().quiet("plugin get ...");
                project.getLogger().quiet(type.includes.toString());

            }
        });
    }


}
