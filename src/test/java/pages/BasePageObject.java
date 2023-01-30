package pages;


import config.SessionData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.WebDriverUtils;

public abstract class BasePageObject {
    public WebDriver webDriver = WebDriverUtils.getWebDriver();
    private ThreadLocal<SessionData> sessionData = new ThreadLocal<>();
    public static SessionData data = new SessionData();
    public String crn;

    protected BasePageObject(PageNavigation expectedTitle) {
        PageFactory.initElements(webDriver, this);
        assertPageTitle(expectedTitle.getPageTitle());
        sessionData.set(data);
        crn = data.getCrn();
    }

    private void assertPageTitle(String expectedTitle) {
        Assert.assertEquals(webDriver.getTitle(), expectedTitle, "Page title does not matches");
    }

    public void clickOnLinkViaText(PageNavigation pageNavigation){
        webDriver.findElement(By.linkText(pageNavigation.getLinkName())).click();
    }

}
