package com.sellmygoods.smgserver;

import com.sellmygoods.smgserver.config.Configuration;
import com.sellmygoods.smgserver.config.ConfigurationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry class for http server [Driver]
 */
public class HttpServer {
    private static final Logger LOG = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        LOG.info("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http-config.json");
        Configuration config = ConfigurationManager.getInstance().getCurrentlyLoadedConfiguration();

        LOG.info("Using port : " + config.getPort());
        LOG.info("Using rootDir : " + config.getRootDir());
    }
}
