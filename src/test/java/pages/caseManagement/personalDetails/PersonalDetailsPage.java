package pages.caseManagement.personalDetails;

import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import navigationPanel.caseManagementLinks.PersonalDetailsNavigationLinks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.BasePageObject;
import pages.caseManagement.CaseSummaryPage;

import static stepDefinitions.BaseSteps.getOffenderSessionData;
import static utils.webDriver.Builder.getWebDriver;

public class PersonalDetailsPage extends BasePageObject implements CaseManagementNavigationLinks, PersonalDetailsNavigationLinks {

    @FindBy(css = "[value = 'Close']")
    private WebElement closeButton;

    @FindBy(id = "SearchForm:FirstName")
    private WebElement firstNameField;

    @FindBy(id = "SearchForm:Surname")
    private WebElement surnameNameField;


    private static final String expectedPageTitle = "Personal Details";
    public PersonalDetailsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public CaseSummaryPage clickOnCloseButton() {
        closeButton.click();
        return new CaseSummaryPage(getWebDriver());
    }

    public void assertOffenderDetails(){
        Assert.assertEquals(getOffenderSessionData().getApiResponseBody().get("firstName"), firstNameField.getText());
        Assert.assertEquals(getOffenderSessionData().getApiResponseBody().get("surname"), surnameNameField.getText());
    }

}
