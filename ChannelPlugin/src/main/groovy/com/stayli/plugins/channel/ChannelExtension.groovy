package com.stayli.plugins.channel

class ChannelExtension {


    List<String> includes = new ArrayList<>();
    List<String> excludes = new ArrayList<>();

    List<String> ajcArgs = new ArrayList<>();

    boolean enabled = true;


    ChannelExtension include(String... filters) {
        if (filters != null) {
            List<String> list = Arrays.asList(filters);
            this.includes.addAll(list);
        }

        return this;
    }

    ChannelExtension exclude(String... filters) {
        if (filters != null) {
            List<String> list = Arrays.asList(filters);
            this.excludes.addAll(list);
        }

        return this;
    }

    ChannelExtension ajcArgs(String... ajcArgs) {
        if (ajcArgs != null) {
            List<String> list = Arrays.asList(ajcArgs);
            this.ajcArgs.addAll(list);
        }

        return this;
    }
}
