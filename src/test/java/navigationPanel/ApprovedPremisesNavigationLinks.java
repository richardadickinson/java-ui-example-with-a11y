package navigationPanel;

import pages.approvedPremisesDiary.ApprovedPremisesDiaryAwaitingArrivalPage;
import pages.approvedPremisesDiary.ApprovedPremisesDiaryCurrentResidentsPage;
import pages.approvedPremisesDiary.ApprovedPremisesDiaryPreviousResidentsPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;

/**
 * This interface contains a list of method returning the appropriate pageObject by clicking on navigation links that become visible
 * under the "Approved Premises Diary" section of the main navigation panel.
 * Classes that implement this interface should ONLY be pageObjects from which these links are visible. e.g. "Approved Premises Diary - Current Residents" Page
 */
public interface ApprovedPremisesNavigationLinks {

    default ApprovedPremisesDiaryCurrentResidentsPage clickOnCurrentResidentsLink() {
        clickOnLinkViaText("Current Residents");
        return new ApprovedPremisesDiaryCurrentResidentsPage(getWebDriver());
    }

    default ApprovedPremisesDiaryPreviousResidentsPage clickOnPreviousResidentsLink() {
        clickOnLinkViaText("Previous Residents");
        return new ApprovedPremisesDiaryPreviousResidentsPage(getWebDriver());
    }

    default ApprovedPremisesDiaryAwaitingArrivalPage clickOnAwaitingArrivalLink() {
        clickOnLinkViaText("Awaiting Arrival");
        return new ApprovedPremisesDiaryAwaitingArrivalPage(getWebDriver());
    }


}
