package pages.caseManagement.eventList;

import navigationPanel.MainNavigationPanelLinks;
import navigationPanel.caseManagementLinks.CaseManagementNavigationLinks;
import navigationPanel.caseManagementLinks.EventListNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class EventDetailsPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks, EventListNavigationLinks {

    private static final String expectedPageTitle = "Event Details";

    public EventDetailsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

}
