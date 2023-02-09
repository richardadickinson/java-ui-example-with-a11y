package pages.casemanagement.eventlist;

import navigationpanel.casemanagementlinks.CaseManagementNavigationLinks;
import navigationpanel.casemanagementlinks.EventListNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class AdditionalOffencesPage extends BasePageObject implements CaseManagementNavigationLinks, EventListNavigationLinks {

    private static final String expectedPageTitle = "Update Additional Offences";

    public AdditionalOffencesPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
