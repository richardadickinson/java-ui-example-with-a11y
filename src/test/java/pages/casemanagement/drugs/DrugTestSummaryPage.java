package pages.casemanagement.drugs;

import navigationpanel.MainNavigationPanelLinks;
import navigationpanel.casemanagementlinks.CaseManagementNavigationLinks;
import navigationpanel.casemanagementlinks.DrugsNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class DrugTestSummaryPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, DrugsNavigationLinks {

    private static final String expectedPageTitle = "Drug Test Summary";

    public DrugTestSummaryPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
