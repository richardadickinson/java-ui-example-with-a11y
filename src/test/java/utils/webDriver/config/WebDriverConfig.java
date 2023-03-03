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

/**
 * A representation of the resources/config.json file
 */
public class WebDriverConfig {

    private static BrowserType browserType;
    private static URL baseUrl;
    private boolean isHeadless;
    private JsonNode testConfig;
    private Map<String, ObjectNode> browserPreferences;
    private static Dimension defaultWindowSize;
    private static String environment;
    private final Logger logger = LoggerFactory.getLogger(WebDriverConfig.class);

    @JsonProperty("browser")
    public void setBrowserType(String browserType) {
        this.browserType = BrowserType.valueOf(browserType.toUpperCase());
        logger.info("Browser type is {}", BrowserType.valueOf(browserType.toUpperCase()));
    }

    public static BrowserType getBrowserType() {
        return browserType;
    }

    @JsonProperty("baseUrl")
    public void setBaseUrl(String baseUrl) throws MalformedURLException {
        String targetBaseUrl;

        switch (getEnvironment()) {
            case "DEFAULT":
            case "delius-test":
                targetBaseUrl = baseUrl;
                this.baseUrl = new URL(targetBaseUrl);
                break;
            case "delius-pre-prod":
                targetBaseUrl = "https://ndelius.pre-prod.delius.probation.hmpps.dsd.io";
                this.baseUrl = new URL(targetBaseUrl);
                break;
            case "delius-stage":
                targetBaseUrl = "https://ndelius.stage.probation.service.justice.gov.uk";
                this.baseUrl = new URL(targetBaseUrl);
                break;
            default:
                throw new MalformedURLException("Could not configure environment URLs");
        }
        logger.info("Base URL set to: " + this.baseUrl);
    }

    public static String getBaseUrl() {
        return baseUrl.toString();
    }

    @JsonProperty("headless")
    public void setHeadless(Boolean isHeadless) {
        this.isHeadless = isHeadless;
        logger.info("WebDriver will run headless: " + isHeadless);
    }

    public Boolean isHeadless() {
        return this.isHeadless;
    }

    @JsonProperty("testConfig")
    public void setTestConfig(JsonNode testConfig) {
        this.testConfig = testConfig;
        logger.info("Custom Test Config set: " + testConfig);
    }

    public String getTestConfig(String item) {
        return this.testConfig.get(item).textValue();
    }

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

    public static Dimension getDefaultWindowSize() {
        return defaultWindowSize;
    }

    @JsonProperty("environment")
    public void setEnvironment(String environment) {
        String remoteEnvironment = System.getenv("ENVIRONMENT");
        if (remoteEnvironment == null) {
            remoteEnvironment = environment;
        }
        this.environment = remoteEnvironment;
        logger.info("Environment set to: " + remoteEnvironment);
    }

    public static String getEnvironment() {
        return environment;
    }

}
