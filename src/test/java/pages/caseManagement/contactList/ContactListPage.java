package pages.caseManagement.contactList;

import navigationPanel.MainNavigationPanelLinks;
import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import navigationPanel.caseManagementLinks.ContactListNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

import static utils.webDriver.Builder.getWebDriver;

public class ContactListPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, ContactListNavigationLinks {

    private static final String expectedPageTitle = "Contact List";

    public ContactListPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public ContactDetailsPage clickOnViewLink() {
        clickOnLinkViaText("view");
        return new ContactDetailsPage(getWebDriver());
    }


}
