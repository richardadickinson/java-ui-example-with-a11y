package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static config.WebDriverConfig.defaultDimensions;

public class WebDriverUtils {
    private static WebDriver webDriver = new ChromeDriver();

    public static void setDriver() {
        webDriver.manage().window().setSize(defaultDimensions);
    }

    public static void navigate(String url){
        webDriver.get(url);
    }

    public static void quit(){
        webDriver.quit();
    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }



}
