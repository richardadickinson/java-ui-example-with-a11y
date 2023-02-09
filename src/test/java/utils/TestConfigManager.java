package utils;

import utils.webdriver.config.WebDriverConfig;

public class TestConfigManager {
    private static TestConfigManager testConfigManager;
    private static WebDriverConfig webDriverConfig;

    /** Load the WebDriverConfig for the test run */
    private TestConfigManager(){
        this.webDriverConfig = new ConfigLoader()
                .chooseTargetConfiguration()
                .build();
    }

    /** 
     * Returns the WebDriverConfig instance for the current test run to access member methods
     * Triggers loading of the config if it isn't populated yet - ensures it's only loaded once
     */
    public static WebDriverConfig get(){
        if (null == testConfigManager) {
            testConfigManager = new TestConfigManager();
        }
        return webDriverConfig;
    }
}