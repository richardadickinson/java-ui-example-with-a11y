package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import static config.WebDriverConfig.defaultDimensions;

public class WebDriverUtils {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    private static ThreadLocal<WebDriver> selectDriver() {
        switch (System.getenv("BROWSER")) {
            case "Safari":
                webDriver.set(new SafariDriver());
                break;
            case "Edge":
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

    public static void setDriver() {
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
