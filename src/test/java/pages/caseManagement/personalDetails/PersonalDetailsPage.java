package pages.caseManagement.personalDetails;

import data.Person;
import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import navigationPanel.caseManagementLinks.PersonalDetailsNavigationLinks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pages.BasePageObject;
import pages.caseManagement.CaseSummaryPage;

import static stepDefinitions.BaseSteps.getSessionData;
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
    private WebElement surnameField;

    @FindBy(id = "SearchForm:Telephone")
    private WebElement telephoneField;

    @FindBy(id = "SearchForm:Sex")
    private WebElement genderField;

    @FindBy(id = "SearchForm:DateOfBirth")
    private WebElement dateOfBirthField;


    private static final String expectedPageTitle = "Personal Details";

    public PersonalDetailsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public CaseSummaryPage clickOnCloseButton() {
        closeButton.click();
        return new CaseSummaryPage(getWebDriver());
    }

    public void assertPersonDetails() {
        Person person = getSessionData().getPerson();
        Assert.assertEquals(person.getCrn(), crnField.getText());
        Assert.assertEquals(person.getTitle(), titleField.getText().toUpperCase());
        Assert.assertEquals(person.getFirstName(), firstNameField.getText());
        Assert.assertEquals(person.getSecondName(), secondNameField.getText());
        Assert.assertEquals(person.getThirdName(), thirdNameField.getText());
        Assert.assertEquals(person.getPreferredName(), preferredNameField.getText());
        Assert.assertEquals(person.getSurname(), surnameField.getText());
        Assert.assertEquals(person.getGender(), genderField.getText());
        Assert.assertEquals(person.getDateOfBirth(), dateOfBirthField.getText());
        Assert.assertEquals(person.getTelephoneNumber(), telephoneField.getText());
    }

    public void simpleAssertOffender() {
        Assert.assertEquals(crnField.getText(), "X289671");
        Assert.assertEquals(firstNameField.getText(), "TomMehWW");
        Assert.assertEquals(surnameField.getText(), "JonKoiYY");
    }

}
