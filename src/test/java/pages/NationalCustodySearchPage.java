package pages;

import navigationpanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;

public class NationalCustodySearchPage extends BasePageObject implements MainNavigationPanelLinks {

    private static final String expectedPageTitle = "National Custody Search";

    public NationalCustodySearchPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
