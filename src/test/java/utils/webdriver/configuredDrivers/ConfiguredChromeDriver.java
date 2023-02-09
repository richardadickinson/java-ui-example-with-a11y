package utils.webdriver.configuredDrivers;

import com.fasterxml.jackson.databind.JsonNode;
import config.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.TestConfigManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConfiguredChromeDriver implements ConfiguredDriver {

    public WebDriver getDriver() throws IOException {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(this.getOptions());
    }

    public ChromeOptions getOptions() throws IOException {
        ChromeOptions opts = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        Iterator<Map.Entry<String, JsonNode>> browserPreferences = TestConfigManager.get()
                .getBrowserPreferences(BrowserType.CHROME)
                .fields();
        while (browserPreferences.hasNext()) {
            Map.Entry<String, JsonNode> entry = browserPreferences.next();
            JsonNode value = entry.getValue();
            String key = entry.getKey();
            switch (value.getNodeType()) {
                case BOOLEAN -> chromePrefs.put(key, value.asBoolean());
                case NUMBER -> chromePrefs.put(key, value.asInt());
                default -> {
                    if (key.equals("download.default_directory")) {
                        chromePrefs.put(key, createFileDownloadDirectory(value.asText()));
                    } else {
                        chromePrefs.put(key, value.asText());
                    }
                }
            }
        }
        opts.setExperimentalOption("prefs", chromePrefs);
        opts.setHeadless(TestConfigManager.get().isHeadless());
        return opts;
    }
}