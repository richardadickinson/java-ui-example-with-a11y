package pages.messageadministration;

import navigationpanel.MainNavigationPanelLinks;
import navigationpanel.MessageAdministrationNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class SpgMessageErrorsPage extends BasePageObject implements MainNavigationPanelLinks, MessageAdministrationNavigationLinks {

    private static final String expectedPageTitle = "SPG Message Errors";

    public SpgMessageErrorsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
