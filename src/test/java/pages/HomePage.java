package pages;

import org.testng.Assert;

import static utils.WebDriverUtils.getWebDriver;

public class HomePage extends BasePageObject {

    public void assertPageTitle() {
        Assert.assertEquals(getWebDriver().getTitle(), "National Delius Home Page");
    }


}
