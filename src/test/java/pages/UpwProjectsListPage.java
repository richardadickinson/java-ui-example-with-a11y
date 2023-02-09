package pages;

import navigationpanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;

public class UpwProjectsListPage extends BasePageObject implements MainNavigationPanelLinks {

    private static final String expectedPageTitle = "UPW Projects List";

    public UpwProjectsListPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
