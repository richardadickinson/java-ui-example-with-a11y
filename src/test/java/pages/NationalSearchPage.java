package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static pages.PageNavigation.NATIONAL_SEARCH;

public class NationalSearchPage extends BasePageObject {
    @FindBy(id = "SearchForm:CRN")
    private WebElement crnInputField;

    @FindBy(id = "SearchForm:searchButton")
    private WebElement searchButton;

    public NationalSearchPage() {
        super(NATIONAL_SEARCH);
    }

    public void searchOffenderWithCrn(){
        crnInputField.sendKeys(crn);
        searchButton.click();
    }


}
