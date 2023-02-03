package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NationalSearchPage extends BasePageObject {

    private String expectedPageTitle = "National Search";
    @FindBy(id = "SearchForm:CRN")
    private WebElement crnInputField;

    @FindBy(id = "SearchForm:searchButton")
    private WebElement searchButton;

    public NationalSearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void searchOffenderWithCrn(String crn) {
        crnInputField.sendKeys(crn);
        searchButton.click();
    }


}
