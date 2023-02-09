package pages;

import navigationpanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;

public class UserManagementPage extends BasePageObject implements MainNavigationPanelLinks {

    private static final String expectedPageTitle = "User Management";

    public UserManagementPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
