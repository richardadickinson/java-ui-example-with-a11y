package navigationPanel.caseManagementLinks;

import pages.caseManagement.contactList.AllContactsPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;


/**
 * This interface contains a list of method returning the appropriate pageObject by clicking on navigation links that become visible
 * under the Contact List section of the main navigation panel.
 * Classes that implement this interface should ONLY be pageObjects from which these links are visible. e.g. "Contact List" Page
 */
public interface ContactListNavigationLinks {

    default AllContactsPage clickOnAllContactsLink() {
        clickOnLinkViaText("All Contacts View");
        return new AllContactsPage(getWebDriver());
    }

}
