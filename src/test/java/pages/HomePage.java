package pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends BasePageObject {

    public static String pageTitle = "National Delius Home Page";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public NationalSearchPage gotToNationalSearch() {
        return new NationalSearchPage(webDriver);
    }


}
