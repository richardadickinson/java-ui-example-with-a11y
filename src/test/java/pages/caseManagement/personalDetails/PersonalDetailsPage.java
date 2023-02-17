package pages.caseManagement.personalDetails;

import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import navigationPanel.caseManagementLinks.PersonalDetailsNavigationLinks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.BasePageObject;
import pages.caseManagement.CaseSummaryPage;

import static stepDefinitions.BaseSteps.*;
import static utils.webDriver.Builder.getWebDriver;

public class PersonalDetailsPage extends BasePageObject implements CaseManagementNavigationLinks, PersonalDetailsNavigationLinks {

    @FindBy(css = "[value = 'Close']")
    private WebElement closeButton;

    @FindBy(id = "SearchForm:crn")
    private WebElement crnField;

    @FindBy(id = "SearchForm:Title")
    private WebElement titleField;

    @FindBy(id = "SearchForm:FirstName")
    private WebElement firstNameField;

    @FindBy(id = "SearchForm:SecondName")
    private WebElement secondNameField;

    @FindBy(id = "SearchForm:ThirdName")
    private WebElement thirdNameField;

    @FindBy(id = "SearchForm:preferredName")
    private WebElement preferredNameField;

    @FindBy(id = "SearchForm:Surname")
    private WebElement surnameNameField;

    @FindBy(id = "SearchForm:Telephone")
    private WebElement telephoneField;


    private static final String expectedPageTitle = "Personal Details";
    public PersonalDetailsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public CaseSummaryPage clickOnCloseButton() {
        closeButton.click();
        return new CaseSummaryPage(getWebDriver());
    }

    public void assertPersonDetails(){
        Assert.assertEquals(getSessionData().getCrn(), crnField.getText());
        Assert.assertEquals(getSessionData().getTitle(), titleField.getText());
        Assert.assertEquals(getSessionData().getFirstName(), firstNameField.getText());
        Assert.assertEquals(getSessionData().getSecondName(), secondNameField.getText());
        Assert.assertEquals(getSessionData().getThirdName(), thirdNameField.getText());
        Assert.assertEquals(getSessionData().getPreferredName(), preferredNameField.getText());
        Assert.assertEquals(getSessionData().getSurname(), surnameNameField.getText());
        Assert.assertEquals(getSessionData().getTelephoneNumber(), telephoneField.getText());
    }

}
