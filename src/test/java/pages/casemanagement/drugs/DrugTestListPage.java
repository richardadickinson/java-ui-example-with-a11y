package pages.casemanagement.drugs;

import navigationpanel.MainNavigationPanelLinks;
import navigationpanel.casemanagementlinks.CaseManagementNavigationLinks;
import navigationpanel.casemanagementlinks.DrugsNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class DrugTestListPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, DrugsNavigationLinks {

    private static final String expectedPageTitle = "Drug Test List";

    public DrugTestListPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
