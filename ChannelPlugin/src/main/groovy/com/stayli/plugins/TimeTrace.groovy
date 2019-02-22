package com.stayli.plugins;

import org.gradle.BuildListener;
import org.gradle.BuildResult;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskExecutionListener;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.tasks.TaskState;

import java.util.HashMap;
import java.util.Map;

public class TimeTrace implements TaskExecutionListener, BuildListener {

    private com.stayli.plugins.Clock clock;
    private HashMap<Long, String> times = new HashMap<>();


    @Override
    public void buildStarted(Gradle gradle) {

    }

    @Override
    public void settingsEvaluated(Settings settings) {

    }

    @Override
    public void projectsLoaded(Gradle gradle) {

    }

    @Override
    public void projectsEvaluated(Gradle gradle) {

    }

    @Override
    public void buildFinished(BuildResult buildResult) {
        for (Map.Entry<Long, String> entry : times.entrySet()) {
            Long key = entry.getKey();
            if (key > 50) {
                System.out.println("Task spend time: --------> " + key
                        + "\n task path: " + entry.getValue());
            }
        }

    }


    @Override
    public void beforeExecute(Task task) {
        clock = new com.stayli.plugins.Clock(System.currentTimeMillis());
    }

    @Override
    public void afterExecute(Task task, TaskState state) {
        long ms = clock.getTimeInMs();
        times.put(ms, task.getPath());
        task.getProject().getLogger().warn("------> " + task.getPath() + " spend" + ms);
    }


}
