package navigationPanel.caseManagementLinks;

import pages.caseManagement.eventList.AdditionalOffencesPage;
import pages.caseManagement.eventList.EventDetailsPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;

/**
 * This interface contains a list of method returning the appropriate pageObject by clicking on navigation links that become visible
 * under the "Event List" subsection of the "Case Management" section.
 * Classes that implement this interface should ONLY be pageObjects from which these links are visible. e.g. "Event Details" Page
 */
public interface EventListNavigationLinks {

    default AdditionalOffencesPage clickOnAdditionalOffencesLink() {
        clickOnLinkViaText("Additional Offences");
        return new AdditionalOffencesPage(getWebDriver());
    }

    default EventDetailsPage clickOnEventDetailsLink() {
        clickOnLinkViaText("Event Details");
        return new EventDetailsPage(getWebDriver());
    }


}
