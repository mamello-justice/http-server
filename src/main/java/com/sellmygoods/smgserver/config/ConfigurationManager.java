package com.sellmygoods.smgserver.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class ConfigurationManager {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationManager.class);

    private static ConfigurationManager configurationManager;
    private static Configuration configuration;

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (configurationManager == null)
            configurationManager = new ConfigurationManager();
        return configurationManager;
    }

    public void loadConfigurationFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            configuration = objectMapper.readValue(new File(path), Configuration.class);
        } catch (IOException e) {
            LOG.error("Trouble reading configuration file");
            e.printStackTrace();
        }
    }

    public Configuration getCurrentlyLoadedConfiguration() {
        if ( configuration == null ) {
            LOG.error("Configuration has not been set");
            throw new RuntimeException("Configuration has not been set.");
        }
        return configuration;
    }


}
