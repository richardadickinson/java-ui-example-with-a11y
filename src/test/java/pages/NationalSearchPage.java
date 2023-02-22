package pages;

import navigationPanel.MainNavigationPanelLinks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.caseManagement.CaseSummaryPage;

import static utils.webDriver.Builder.getWebDriver;
import static utils.webDriver.interactions.FindBy.findByUntil;
import static utils.webDriver.interactions.SearchResultsTable.selectSearchResultsTableViewLink;

public class NationalSearchPage extends BasePageObject implements MainNavigationPanelLinks {

    private static final String expectedPageTitle = "National Search";

    private static final String searchResultsTableId = "offendersTable";
    private static final String searchResultsPopulatedXpath = "//*[@id='offendersTable']/tbody/tr[1]/td[11]/a";
    @FindBy(id = "SearchForm:CRN")
    private WebElement crnInputField;

    @FindBy(id = "SearchForm:LastName")
    private WebElement lastNameField;

    @FindBy(id="SearchForm:FirstName")
    private WebElement firstNameField;

    @FindBy(id = "SearchForm:searchButton")
    private WebElement searchButton;

    public NationalSearchPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public static String getSearchResultsTableId() {
        return searchResultsTableId;
    }

    public NationalSearchPage enterCrnAndSearch(String crn) {
        crnInputField.sendKeys(crn);
        searchAndWaitForResults();
        return this;
    }

    public NationalSearchPage enterPersonNameAndSearch(String firstName, String lastName) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        searchAndWaitForResults();
        return this;
    }

    public CaseSummaryPage clickOnViewLink_singleResult() {
        clickOnLinkViaText("View");
        return new CaseSummaryPage(webDriver);
    }

    public CaseSummaryPage selectSearchResultsViewLinkByCRN(String crn) {
        selectSearchResultsTableViewLink(searchResultsTableId, "CRN", crn);
        return new CaseSummaryPage(getWebDriver());
    }

    private void searchAndWaitForResults(){
        searchButton.click();
        findByUntil(getWebDriver(), By.xpath(searchResultsPopulatedXpath));
    }

}
