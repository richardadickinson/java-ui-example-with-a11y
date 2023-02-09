package pages.casemanagement.eventlist;

import navigationpanel.MainNavigationPanelLinks;
import navigationpanel.casemanagementlinks.CaseManagementNavigationLinks;
import org.openqa.selenium.WebDriver;
import pages.BasePageObject;

public class EventsPage extends BasePageObject implements MainNavigationPanelLinks, CaseManagementNavigationLinks {

    private static final String expectedPageTitle = "Events";

    public EventsPage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public EventDetailsPage clickOnViewLink(){
        clickOnLinkViaText("view");
        return new EventDetailsPage(webDriver);
    }
}
