package com.stayli.channelplugin;

public class Clock {
    long startTimeInMs;

    Clock(long startTimeInMs) {
        this.startTimeInMs = startTimeInMs;
    }

    public long getTimeInMs() {
        return System.currentTimeMillis() - startTimeInMs;
    }
}
