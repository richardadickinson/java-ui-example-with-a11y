package utils.webDriver.configuredDrivers;

import com.fasterxml.jackson.databind.JsonNode;
import config.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.TestConfigManager;
import utils.webdriver.configuredDrivers.ConfiguredDriver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConfiguredEdgeDriver implements ConfiguredDriver {

    @Override
    public WebDriver getDriver() throws IOException {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(this.getOptions());
    }

    @Override
    public EdgeOptions getOptions() throws IOException {

        EdgeOptions options = new EdgeOptions();
        Map<String, Object> edgePrefs = new HashMap<>();
        Iterator<Map.Entry<String, JsonNode>> browserPreferences = TestConfigManager.get()
                .getBrowserPreferences(BrowserType.EDGE)
                .fields();
        while (browserPreferences.hasNext()) {
            Map.Entry<String, JsonNode> entry = browserPreferences.next();
            JsonNode value = entry.getValue();
            String key = entry.getKey();
            switch (value.getNodeType()) {
                case BOOLEAN -> edgePrefs.put(key, value.asBoolean());
                case NUMBER -> edgePrefs.put(key, value.asInt());
                default -> {
                    if (key.equals("download.default_directory")) {
                        edgePrefs.put(key, createFileDownloadDirectory(value.asText()));
                    } else {
                        edgePrefs.put(key, value.asText());
                    }
                }
            }
        }
        options.setExperimentalOption("prefs", edgePrefs);
        options.setHeadless(TestConfigManager.get().isHeadless());

        return options;
    }
}