package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;

import static config.WebDriverConfig.defaultDimensions;

public class WebDriverUtils {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    private static ThreadLocal<WebDriver> selectDriver() throws IOException {
        String browser = PropertiesFileReader.getPropertyValueFromFile("browser");
        switch (browser) {
            case "safari":
                webDriver.set(new SafariDriver());
                break;
            case "edge":
                webDriver.set(new EdgeDriver());
                break;
            default:
                webDriver.set(new ChromeDriver());
        }
        return webDriver;
    }

    public static WebDriver getWebDriver(){
        return webDriver.get();
    }

    public static void setDriver() throws IOException {
        selectDriver();
        webDriver.get().manage().window().setSize(defaultDimensions);
    }

    public static void navigate(String url){
        webDriver.get().get(url);
    }

    public static void quit(){
        webDriver.get().manage().deleteAllCookies();
        webDriver.get().quit();
    }

}
