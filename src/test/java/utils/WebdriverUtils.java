package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static config.WebdriverConfig.defaultDimensions;

public class WebdriverUtils {

    WebDriver webdriver;

    private void setDriver() {
        webdriver = new ChromeDriver();
        webdriver.manage().window().setSize(defaultDimensions);
    }

    public void launchBrowser(String url){
        setDriver();
        webdriver.get(url);
    }




}
