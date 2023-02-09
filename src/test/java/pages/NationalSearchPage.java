package pages;

import navigationpanel.MainNavigationPanelLinks;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.casemanagement.CaseSummaryPage;

public class NationalSearchPage extends BasePageObject implements MainNavigationPanelLinks {

    private static final String expectedPageTitle = "National Search";

    @FindBy(id = "SearchForm:CRN")
    private WebElement crnInputField;

    @FindBy(id = "SearchForm:searchButton")
    private WebElement searchButton;

    public NationalSearchPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public NationalSearchPage enterCrnAndClickSearchButton(String crn) {
        crnInputField.sendKeys(crn);
        searchButton.click();
        return this;
    }

    public CaseSummaryPage clickViewLink() {
        try {
            clickOnLinkViaText("View");
        } catch (NoSuchElementException e) {
            System.out.println(e + " thrown, retrying");
            clickOnLinkViaText("View");
        }
        return new CaseSummaryPage(webDriver);
    }


}
