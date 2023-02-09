package pages.casemanagement.eventlist;

import navigationpanel.MainNavigationPanelLinks;
import navigationpanel.casemanagementlinks.CaseManagementNavigationLinks;
import navigationpanel.casemanagementlinks.EventListNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class EventDetailsPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, EventListNavigationLinks {

    private static final String expectedPageTitle = "Event Details";

    public EventDetailsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

}
