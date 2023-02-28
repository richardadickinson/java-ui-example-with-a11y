package utils;

import utils.webDriver.config.WebDriverConfig;

import java.io.IOException;

public class ConfigLoader {
    private final String TEST_CONFIGURATION_FILE = "test-config.json";
    private final String STAGE_CONFIGURATION_FILE = "stage-config.json";
    private final String PRE_PROD_CONFIGURATION_FILE = "pre-prod-config.json";

    private String targetConfigurationFile;

    /**
     * Method for figuring out if we're using the internal, default configuration,
     * or we're setting a reference to an external configuration file (from the file system).
     *
     * @return ConfigurationLoader builder pattern so returns self
     */
    public ConfigLoader chooseTargetConfiguration() {

        String configurationProperty = System.getenv("ENVIRONMENT");

        if (configurationProperty == null) {
            configurationProperty = "DEFAULT";
        }

        switch (configurationProperty) {
            case "delius-stage":
                this.targetConfigurationFile = STAGE_CONFIGURATION_FILE;
                return this;
            case "delius-pre-prod":
                this.targetConfigurationFile = PRE_PROD_CONFIGURATION_FILE;
                return this;
            case "delius-test":
            case "DEFAULT":
                this.targetConfigurationFile = TEST_CONFIGURATION_FILE;
                return this;
            default:
                throw new IllegalStateException("Could not set Environment configuration with value: " + configurationProperty);
        }
    }

    /**
     * This method builds the configuration and returns it
     */
    public WebDriverConfig build() {
        try {
            return JsonUtils.fromFile(
                    ConfigFileReader.loadFromClasspathOrFileSystem(targetConfigurationFile), WebDriverConfig.class);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration from " + targetConfigurationFile);
        }
    }
}