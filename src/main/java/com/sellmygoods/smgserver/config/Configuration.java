package com.sellmygoods.smgserver.config;

public class Configuration {

    private final int port;
    private final String rootDir;

    public Configuration() {
        this.port = 0;
        this.rootDir = "";
    }

    public Configuration(int port, String rootDir) {
        this.port = port;
        this.rootDir = rootDir;
    }

    public int getPort() {
        return port;
    }

    public String getRootDir() {
        return rootDir;
    }

}
