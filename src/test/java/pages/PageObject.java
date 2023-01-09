package pages;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {


    protected WebDriver webDriver;
    protected PageObject(WebDriver webDriver){
        this.webDriver=webDriver;
    }

    public WebDriver getWebDriver(){
        return this.webDriver;

    }


}
