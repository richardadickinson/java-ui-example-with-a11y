package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;

import static config.WebDriverConfig.defaultDimensions;

public class WebDriverUtils {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    private static void selectDriver() throws IOException {
        String browser = PropertiesFileReader.getPropertyValueFromFile("browser");
        switch (browser) {
            case "safari" -> webDriver.set(new SafariDriver());
            case "edge" -> webDriver.set(new EdgeDriver());
            default -> webDriver.set(new ChromeDriver());
        }
    }

    public static WebDriver getWebDriver() {
        return webDriver.get();
    }

    public static void setDriver() throws IOException {
        selectDriver();
        getWebDriver().manage().window().setSize(defaultDimensions);
    }

    public static void navigate(String url) {
        System.out.println("URL: " + url); //DEBUG
        getWebDriver().get(url);
    }

    public static void quit() {
        WebDriver wd = getWebDriver();
        wd.manage().deleteAllCookies();
        wd.quit();
    }

}
