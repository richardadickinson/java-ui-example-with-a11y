package navigationpanel.casemanagementlinks;

import pages.casemanagement.drugs.DrugTestListPage;
import pages.casemanagement.drugs.DrugTestSummaryPage;
import pages.casemanagement.drugs.DrugsHistoryPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;

/**
 * This interface contains a list of method returning the appropriate pageObject by clicking on navigation links that become visible
 * under the "Drugs" subsection of the "Case Management" section.
 * Classes that implement this interface should ONLY be pageObjects from which these links are visible. e.g. "Drug Test Summary" Page
 */
public interface DrugsNavigationLinks {

    default DrugTestSummaryPage clickOnSummaryViewLink() {
        clickOnLinkViaText("Summary View");
        return new DrugTestSummaryPage(getWebDriver());
    }

    default DrugsHistoryPage clickOnHistoryViewLink() {
        clickOnLinkViaText("History View");
        return new DrugsHistoryPage(getWebDriver());
    }

    default DrugTestListPage clickOnTestListLink() {
        clickOnLinkViaText("Test List");
        return new DrugTestListPage(getWebDriver());
    }


}
