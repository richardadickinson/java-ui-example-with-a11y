package pages.caseManagement.eventList;

import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import navigationPanel.caseManagementLinks.EventListNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class AdditionalOffencesPage extends BasePageObject implements CaseManagementNavigationLinks, EventListNavigationLinks {

    private static final String expectedPageTitle = "Update Additional Offences";

    public AdditionalOffencesPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }
}
