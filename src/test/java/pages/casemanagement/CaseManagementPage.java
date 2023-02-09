package pages.casemanagement;

import navigationpanel.MainNavigationPanelLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class CaseManagementPage extends BasePageObject implements MainNavigationPanelLinks {

    private static final String expectedPageTitle = "Case Management";

    public CaseManagementPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
