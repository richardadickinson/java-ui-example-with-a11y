package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.webDriver.interactions.Click;
import utils.webDriver.interactions.FindBy;

import static utils.webDriver.Builder.getWebDriver;

public abstract class BasePageObject {
    protected static WebDriver webDriver;

    protected BasePageObject(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public static void assertPageTitle(String expectedTitle) {
        Assert.assertEquals(getWebDriver().getTitle(), expectedTitle, "Page title does not match");
    }

    public static void clickOnLinkViaText(String linkText) {
        FindBy.findByUntil(getWebDriver(), By.linkText(linkText), 30);
        Click.clickUntil(FindBy.findByLinkText(getWebDriver(), linkText));
    }

}
