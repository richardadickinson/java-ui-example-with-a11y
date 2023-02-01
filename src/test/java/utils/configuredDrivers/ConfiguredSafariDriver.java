package utils.configuredDrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.Map;

public class ConfiguredSafariDriver implements ConfiguredDriver {

    public WebDriver getDriver() {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver(getOptions());

    }

    /** Setting headless is not currently supported by Safari */
    public SafariOptions getOptions() {
        SafariOptions options = new SafariOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("safari.options.dataDir", "target/safari-run-generated-files");  // this doesn't work!!
        options.setCapability("cloud:options", prefs);

        return options;
    }
}
