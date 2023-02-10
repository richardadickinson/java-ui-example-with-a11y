package navigationPanel.caseManagementLinks;

import pages.caseManagement.CaseSummaryPage;
import pages.caseManagement.DocumentListPage;
import pages.caseManagement.SubjectAccessReportListPage;
import pages.caseManagement.contactList.ContactListPage;
import pages.caseManagement.drugs.DrugTestSummaryPage;
import pages.caseManagement.eventList.EventsPage;
import pages.caseManagement.personalDetails.PersonalDetailsPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;

/**
 * This interface contains a list of method returning the appropriate pageObject by clicking on navigation links that become visible
 * under the "Case Management" section of the main navigation panel.
 * Classes that implement this interface should ONLY be pageObjects from which these links are visible. e.g. "Case Summary" Page
 */
public interface CaseManagementNavigationLinks {

    default CaseSummaryPage clickOnCaseSummaryLink() {
        clickOnLinkViaText("Case Summary");
        return new CaseSummaryPage(getWebDriver());
    }

    default PersonalDetailsPage clickOnPersonalDetailsLink() {
        clickOnLinkViaText("Personal Details");
        return new PersonalDetailsPage(getWebDriver());
    }

    default DrugTestSummaryPage clickOnDrugsLink() {
        clickOnLinkViaText("Drugs");
        return new DrugTestSummaryPage(getWebDriver());
    }

    default EventsPage clickOnEventListLink() {
        clickOnLinkViaText("Event List");
        return new EventsPage(getWebDriver());
    }

    default ContactListPage clickOnContactListLink() {
        clickOnLinkViaText("Contact List");
        return new ContactListPage(getWebDriver());
    }

    default DocumentListPage clickOnDocumentListLink() {
        clickOnLinkViaText("Document List");
        return new DocumentListPage(getWebDriver());
    }

    default SubjectAccessReportListPage clickOnSubjectAccessReportsLink() {
        clickOnLinkViaText("Subject Access Reports");
        return new SubjectAccessReportListPage(getWebDriver());
    }
}
