package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WebDriverUtils;

public abstract class BasePageObject {

    private WebDriver webDriver = WebDriverUtils.getWebDriver();

    protected BasePageObject() {
        PageFactory.initElements(webDriver, this);
    }

    public void assertPageTitle(String pageTitle) {
        Assert.assertEquals(webDriver.getTitle(), pageTitle, "Page title matches");
    }

}
