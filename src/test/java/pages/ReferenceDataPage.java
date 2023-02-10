package pages;

import navigationPanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;

public class ReferenceDataPage extends BasePageObject implements MainNavigationPanelLinks {

    private static final String expectedPageTitle = "Reference Data";

    public ReferenceDataPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
