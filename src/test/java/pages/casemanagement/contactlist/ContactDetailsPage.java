package pages.casemanagement.contactlist;

import navigationpanel.MainNavigationPanelLinks;
import navigationpanel.casemanagementlinks.CaseManagementNavigationLinks;
import navigationpanel.casemanagementlinks.ContactListNavigationLinks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePageObject;

public class ContactDetailsPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, ContactListNavigationLinks {

    private static final String expectedPageTitle = "Contact Details";

    @FindBy(css = "[value = 'Close']")
    WebElement closeButton;

    public ContactDetailsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public ContactListPage clickOnCloseButton() {
        closeButton.click();
        return new ContactListPage(webDriver);
    }


}
