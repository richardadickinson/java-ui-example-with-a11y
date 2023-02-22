package pages.caseManagement.contactList;

import navigationPanel.MainNavigationPanelLinks;
import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import navigationPanel.caseManagementLinks.ContactListNavigationLinks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.BasePageObject;

import static utils.DateUtil.getTodayDateFormatted;
import static utils.webDriver.Builder.getWebDriver;

public class ContactDetailsPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, ContactListNavigationLinks {

    private static final String expectedPageTitle = "Contact Details";

    @FindBy(css = "[value = 'Close']")
    WebElement closeButton;

    @FindBy(id="searchContactForm:Date")
    WebElement dateField;

    public ContactDetailsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public ContactListPage clickOnCloseButton() {
        closeButton.click();
        return new ContactListPage(getWebDriver());
    }

    public void assertContactDetails(){
        Assert.assertEquals(dateField.getText(), getTodayDateFormatted(), "contact date does not match");
    }

}
