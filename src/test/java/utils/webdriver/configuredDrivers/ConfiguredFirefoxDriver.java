package utils.webDriver.configuredDrivers;

import com.fasterxml.jackson.databind.JsonNode;
import config.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.TestConfigManager;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ConfiguredFirefoxDriver implements ConfiguredDriver {

    public WebDriver getDriver() throws IOException {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(this.getOptions());
    }

    public FirefoxOptions getOptions() throws IOException {
        FirefoxOptions options = new FirefoxOptions();
        Iterator<Map.Entry<String, JsonNode>> browserPreferences = TestConfigManager.get()
                .getBrowserPreferences(BrowserType.FIREFOX)
                .fields();
        while (browserPreferences.hasNext()) {
            Map.Entry<String, JsonNode> entry = browserPreferences.next();
            JsonNode value = entry.getValue();
            String key = entry.getKey();
            switch (value.getNodeType()) {
                case BOOLEAN -> options.addPreference(key, value.asBoolean());
                case NUMBER -> options.addPreference(key, value.asInt());
                default -> {
                    if (key.equals("browser.download.dir")) {
                        options.addPreference(key, createFileDownloadDirectory(value.asText()));
                    } else {
                        options.addPreference(key, value.asText());
                    }
                }
            }
        }

        options.setHeadless(TestConfigManager.get().isHeadless());
        return options;
    }

}
