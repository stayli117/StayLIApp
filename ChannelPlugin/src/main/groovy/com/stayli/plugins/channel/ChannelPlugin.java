package com.stayli.plugins.channel;


import org.gradle.api.Project;
import org.gradle.api.plugins.ExtensionContainer;

public class ChannelPlugin {


    public static void apply(Project project) {
        //创建渠道扩展
        final ExtensionContainer extensions = project.getExtensions();
        extensions.create("channel", ChannelExtension.class);
        // 获取 channel 配置
        ChannelExtension type = extensions.getByType(ChannelExtension.class);
        project.getLogger().quiet(type.includes.toString());
    }
}
