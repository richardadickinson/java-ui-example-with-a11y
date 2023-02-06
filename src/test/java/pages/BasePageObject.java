package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public abstract class BasePageObject {
    public WebDriver webDriver;

    protected BasePageObject(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void assertPageTitle(String expectedTitle) {
        Assert.assertEquals(webDriver.getTitle(), expectedTitle, "Page title does not matches");
    }

    public void clickOnLinkViaText(String linkText){
        webDriver.findElement(By.linkText(linkText)).click();
    }

}
