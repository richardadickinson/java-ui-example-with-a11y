package utils.webDriver;

import config.BrowserType;
import org.openqa.selenium.WebDriver;
import utils.TestConfigManager;
import utils.webdriver.configuredDrivers.ConfiguredChromeDriver;
import utils.webdriver.configuredDrivers.ConfiguredFirefoxDriver;
import utils.webdriver.configuredDrivers.ConfiguredSafariDriver;

import java.io.IOException;

public class Builder {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static void initialiseWebDriver() throws IOException {
        BrowserType browser = TestConfigManager.get().getBrowserType();
        switch (browser) {
            case CHROME -> webDriver.set(new ConfiguredChromeDriver().getDriver());
            case SAFARI -> webDriver.set(new ConfiguredSafariDriver().getDriver());
            case EDGE -> webDriver.set(new utils.webDriver.configuredDrivers.ConfiguredEdgeDriver().getDriver());
            case FIREFOX -> webDriver.set(new ConfiguredFirefoxDriver().getDriver());
            default -> throw new RuntimeException("No valid target browser is set WebDriverConfig");
        }
        webDriver.get().manage().window().setSize(TestConfigManager.get().getDefaultWindowSize());
    }

}