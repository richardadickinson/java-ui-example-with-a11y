package utils;

import config.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.configuredDrivers.ConfiguredChromeDriver;

import java.io.IOException;

public class WebDriverUtils {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static ThreadLocal<WebDriver> initialiseWebDriver() throws IOException {
        BrowserType browser = TestConfigManager.get().getBrowserType();
        switch (browser) {
            case CHROME -> webDriver.set(new ConfiguredChromeDriver().getDriver());
            case SAFARI -> webDriver.set(new SafariDriver());
            case EDGE -> webDriver.set(new EdgeDriver());
            case FIREFOX -> webDriver.set(new FirefoxDriver());
            default -> throw new RuntimeException("No valid target browser is set WebDriverConfig");
        }
        webDriver.get().manage().window().setSize(TestConfigManager.get().getDefaultWindowSize());
        return webDriver;
    }

}
