package navigationpanel;

import pages.officerdiary.AlertsDiaryPage;
import pages.officerdiary.CaseloadDiaryPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;

/**
 * This interface contains a list of method returning the appropriate pageObject by clicking on navigation links that become visible
 * under the "Officer Diary" section of the main navigation panel.
 * Classes that implement this interface should ONLY be pageObjects from which these links are visible. e.g. "Caseload Diary" Page
 */
public interface OfficerDiaryNavigationLinks {

    default CaseloadDiaryPage clickOnCaseloadDiaryLink() {
        clickOnLinkViaText("Caseload");
        return new CaseloadDiaryPage(getWebDriver());
    }

    default AlertsDiaryPage clickOnAlertsDiaryLink() {
        clickOnLinkViaText("Alerts Diary");
        return new AlertsDiaryPage(getWebDriver());
    }

}
