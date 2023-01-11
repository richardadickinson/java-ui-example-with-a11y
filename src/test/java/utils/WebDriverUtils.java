package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;

import static config.WebDriverConfig.defaultDimensions;
import static utils.PropertiesFileReader.getPropValue;

public class WebDriverUtils {

    private static WebDriver webDriver;

    static {
        try {
            webDriver = selectDriver();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static WebDriver selectDriver() throws IOException {
        String driverType = getPropValue("browser");
        switch (driverType) {
            case "Safari":
                webDriver = new SafariDriver();
                break;
            case "Edge":
                webDriver = new EdgeDriver();
                break;
            default:
                webDriver = new ChromeDriver();
        }
        return webDriver;
    }

    public static void setDriver() {
        webDriver.manage().window().setSize(defaultDimensions);
    }

    public static void navigate(String url){
        webDriver.get(url);
    }

    public static void quit(){
        webDriver.manage().deleteAllCookies();
        webDriver.quit();
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

}
