package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public abstract class BasePageObject {

    private WebDriver webDriver;

    protected BasePageObject(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void assertPageTitle(String pageTitle) {
        Assert.assertEquals(webDriver.getTitle(), pageTitle, "Page title matches");
    }

    public WebElement findByIdOrName(String idOrName) {
        return webDriver.findElement(new ByIdOrName(idOrName));
    }

}
