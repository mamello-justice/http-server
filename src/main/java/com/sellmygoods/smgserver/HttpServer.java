package com.sellmygoods.smgserver;

import com.sellmygoods.smgserver.config.Configuration;
import com.sellmygoods.smgserver.config.ConfigurationManager;
import com.sellmygoods.smgserver.core.SocketAccepterThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Entry class for http server [Driver]
 */
public class HttpServer {
    private static final Logger LOG = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        LOG.info("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http-config.json");
        Configuration config = ConfigurationManager.getInstance().getCurrentlyLoadedConfiguration();

        LOG.info("Using port : {}", config.getPort());
        LOG.info("Using rootDir : {}", config.getRootDir());

        try {
            SocketAccepterThread socketAccepterThread = new SocketAccepterThread(config.getPort(), config.getRootDir());
            socketAccepterThread.start();
        } catch (IOException e) {
            LOG.error("Trouble creating server socket");
        }

    }
}
