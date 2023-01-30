
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePageObject;

public class NationalSearchPage extends BasePageObject {

    @FindBy(id = "SearchForm:CRN")
    private WebElement crnInputField;

    @FindBy(id = "SearchForm:searchButton")
    private WebElement searchButton;

    protected NationalSearchPage() {
        super(webDriver);
    }

    public void searchOffenderWithCrn(String crn) {
        crnInputField.sendKeys(crn);
        searchButton.click();
    }

}