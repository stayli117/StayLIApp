package com.stayli.plugins.channel;

import org.gradle.api.DefaultTask;
import org.gradle.api.internal.tasks.TaskExecuter;

public class ChnnelTask extends DefaultTask {

    public ChnnelTask() {
        // 分组
        setGroup("channel");

        // 描述
        setDescription("des");
    }

    @Override
    public void setExecuter(TaskExecuter executer) {
        super.setExecuter(executer);
    }
}
