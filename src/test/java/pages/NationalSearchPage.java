package pages;

import navigationPanel.MainNavigationPanelLinks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.caseManagement.CaseSummaryPage;

import java.util.List;

import static utils.webDriver.Builder.getWebDriver;
import static utils.webDriver.interactions.FindBy.findByUntil;

public class NationalSearchPage extends BasePageObject implements MainNavigationPanelLinks {

    private final static Logger logger = LoggerFactory.getLogger(NationalSearchPage.class);
    private static final String expectedPageTitle = "National Search";

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
        List<WebElement> crnCells = getWebDriver().findElements(By.xpath("//table[@id='offendersTable']/tbody/tr/td[1]"));
        logger.debug("Search results row count: " + crnCells.size());
        int index = 1;
        for (WebElement cell: crnCells) {
            if (cell.getText().contains(crn)) {
                logger.debug("CRN found, clicking View link...");
                getWebDriver().findElement(By.xpath("//*[@id='offendersTable']/tbody/tr["+index+"]/td[11]/a")).click();
                break;
            }
            index++;
        }
        logger.debug("Search result Index selected = " + index);
        return new CaseSummaryPage(getWebDriver());
    }

    private void searchAndWaitForResults(){
        searchButton.click();
        findByUntil(getWebDriver(), By.xpath("//*[@id='offendersTable']/tbody/tr[1]/td[11]/a"));
    }

}
