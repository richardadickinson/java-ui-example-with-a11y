package pages.caseManagement.personalDetails;

import navigationPanel.MainNavigationPanelLinks;
import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class AdditionalIdentifiersPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks {

    private static final String expectedPageTitle = "Additional Identifiers";

    public AdditionalIdentifiersPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
