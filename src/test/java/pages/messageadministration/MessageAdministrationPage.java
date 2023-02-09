package pages.messageadministration;

import navigationpanel.MainNavigationPanelLinks;
import navigationpanel.MessageAdministrationNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class MessageAdministrationPage extends BasePageObject implements MainNavigationPanelLinks, MessageAdministrationNavigationLinks {

    private static final String expectedPageTitle = "Message Administration";

    public MessageAdministrationPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
