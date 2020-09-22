package com.sellmygoods.smgserver.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sellmygoods.smgserver.http.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

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
            LOG.error(" ✗ Problem reading configuration file", e);
        }
    }

    public void loadHttpValidHeaders(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HttpHeader.validHeaders = new ArrayList<>();
        try {
            HttpHeader.validHeaders = objectMapper.readValue(new File(path), objectMapper
                    .getTypeFactory()
                    .constructCollectionType(List.class, HttpHeader.class));
        } catch (IOException e) {
            LOG.error(" ✗ Problem reading valid headers", e);
        }
    }

    public Configuration getCurrentlyLoadedConfiguration() {
        if ( configuration == null ) {
            LOG.error("Configuration has not been set", new RuntimeException("Configuration has not been set."));
        }
        return configuration;
    }


}
