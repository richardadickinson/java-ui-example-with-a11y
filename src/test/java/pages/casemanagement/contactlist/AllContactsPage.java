package pages.casemanagement.contactlist;

import navigationpanel.MainNavigationPanelLinks;
import navigationpanel.casemanagementlinks.CaseManagementNavigationLinks;
import navigationpanel.casemanagementlinks.ContactListNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class AllContactsPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, ContactListNavigationLinks {

    private static final String expectedPageTitle = "Contact List";

    public AllContactsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
