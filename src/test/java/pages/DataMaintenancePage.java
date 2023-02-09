package pages;

import org.openqa.selenium.WebDriver;

public class DataMaintenancePage extends BasePageObject {

    private static final String expectedPageTitle = "Data Maintenance";

    public DataMaintenancePage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
