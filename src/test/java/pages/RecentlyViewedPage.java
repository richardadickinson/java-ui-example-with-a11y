package pages;

import navigationPanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecentlyViewedPage extends BasePageObject implements MainNavigationPanelLinks {

    private static final String expectedPageTitle = "Recently Viewed Records";

    @FindBy(css = "[value = 'Close']")
    WebElement closeButton;

    public RecentlyViewedPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public HomePage clickOnCloseButton() {
        closeButton.click();
        return new HomePage(webDriver);
    }


}
