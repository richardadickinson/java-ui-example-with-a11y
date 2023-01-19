package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WebDriverUtils;

import static utils.WebDriverUtils.getWebDriver;

public abstract class BasePageObject {

    private WebDriver webDriver = WebDriverUtils.getWebDriver();

    protected BasePageObject() {
        PageFactory.initElements(webDriver, this);
    }

    public void assertPageTitle(String pageTitle) {
        Assert.assertEquals(getWebDriver().getTitle(), pageTitle);
    }

}
