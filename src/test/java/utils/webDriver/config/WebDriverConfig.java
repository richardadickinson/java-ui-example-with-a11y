package utils.webDriver.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import config.BrowserType;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;

/** A representation of the resources/config.json file */
public class WebDriverConfig {

    private static BrowserType browserType;
    private static URL baseUrl;
    private static String databaseUsername;
    private static String databaseUrl;
    private boolean isHeadless;
    private JsonNode testConfig;
    private Map<String, ObjectNode> browserPreferences;
    private static Dimension defaultWindowSize;
    private final Logger logger = LoggerFactory.getLogger(WebDriverConfig.class);

    @JsonProperty("browser")
    public void setBrowserType(String browserType) {
        this.browserType = BrowserType.valueOf(browserType.toUpperCase());
        logger.info("Browser type is {}", BrowserType.valueOf(browserType.toUpperCase()));
    }
    public static BrowserType getBrowserType() { return browserType; }

    @JsonProperty("baseUrl")
    public void setBaseUrl(String baseUrl) throws MalformedURLException {
        String targetBaseUrl = System.getProperty("baseUrl", baseUrl);
        this.baseUrl = new URL(targetBaseUrl);
        logger.info("Base URL set to: " + baseUrl);
    }
    public static String getBaseUrl() { return baseUrl.toString(); }

    @JsonProperty("databaseUsername")
    public void setDatabaseUsername(String dbUsername) {
        this.databaseUsername = dbUsername;
        logger.info("Database username set to: " + dbUsername);
    }
    public static String getDatabaseUsername() { return databaseUsername; }

    @JsonProperty("databaseUrl")
    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        logger.info("Database URL set to: " + databaseUrl);
    }
    public static String getDatabaseUrl() { return databaseUrl; }

    @JsonProperty("headless")
    public void setHeadless(Boolean isHeadless) {
        this.isHeadless = isHeadless;
        logger.info("WebDriver will run headless: " + isHeadless);
    }
    public Boolean isHeadless() { return this.isHeadless; }

    @JsonProperty("testConfig")
    public void setTestConfig(JsonNode testConfig) {
        this.testConfig = testConfig;
        logger.info("Custom Test Config set: " + testConfig);
    }
    public String getTestConfig(String item) { return this.testConfig.get(item).textValue(); }

    @JsonProperty("browserPreferences")
    public void setBrowserPreferences(Map<String, ObjectNode> browserPreferences) {
        this.browserPreferences = browserPreferences;
    }
    public ObjectNode getBrowserPreferences(BrowserType browserType) {
        ObjectNode browserTypePrefs = Optional.ofNullable(browserPreferences)
                .flatMap(options -> options.entrySet().stream()
                        .filter(entry -> entry.getKey().equalsIgnoreCase((browserType.toString())))
                        .findFirst()
                        .map(Map.Entry::getValue)
                )
                .orElse(JsonNodeFactory.instance.objectNode());
        logger.info("Browser Preferences set: " + browserTypePrefs);
        return browserTypePrefs;
    }

    @JsonProperty("defaultWindowSize")
    public void setDefaultDimension(String defaultWindowSize) {
        String[] parts = defaultWindowSize.split("x");
        try {
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            this.defaultWindowSize = new Dimension(x, y);
            logger.info("Window size set to: " + defaultWindowSize);
        } catch (NumberFormatException e) {
            logger.warn("Window size string received does not conform to format: <digits>x<digits>");
        }
    }
    public static Dimension getDefaultWindowSize() { return defaultWindowSize; }
}
