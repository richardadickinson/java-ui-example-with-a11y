package navigationPanel;

import pages.*;
import pages.approvedPremisesDiary.ApprovedPremisesDiaryCurrentResidentsPage;
import pages.caseManagement.CaseManagementPage;
import pages.courtDiary.CourtDiaryAppearancesPage;
import pages.messageAdministration.MessageAdministrationPage;
import pages.officerDiary.CaseloadDiaryPage;

import static pages.BasePageObject.clickOnLinkViaText;
import static utils.webDriver.Builder.getWebDriver;

/**
 * This interface defines the Main navigation panel visible throughout most of the application
 * must only be implemented on pageObject classes that require access to that section
 * example: This panel is not visible on the Home page, therefore should not be implemented on the HomePage class
 */
public interface MainNavigationPanelLinks {

    default HomePage clickOnHomeLink() {
        clickOnLinkViaText("Home");
        return new HomePage(getWebDriver());
    }

    default NationalSearchPage clickOnNationalSearchLink() {
        clickOnLinkViaText("National Search");
        return new NationalSearchPage(getWebDriver());
    }

    default NationalCustodySearchPage clickOnNationalCustodySearchLink() {
        clickOnLinkViaText("National Custody Search");
        return new NationalCustodySearchPage(getWebDriver());
    }

    default RecentlyViewedPage clickOnRecentlyViewedLink() {
        clickOnLinkViaText("Recently Viewed");
        return new RecentlyViewedPage(getWebDriver());
    }

    default CaseManagementPage clickOnCaseManagementLink() {
        clickOnLinkViaText("Case Management");
        return new CaseManagementPage(getWebDriver());
    }

    default UpwProjectDiaryPage clickOnUpwProjectDiaryLink() {
        clickOnLinkViaText("UPW Project Diary");
        return new UpwProjectDiaryPage(getWebDriver());
    }

    default UpwProjectsListPage clickOnUpwProjectsLink() {
        clickOnLinkViaText("UPW Projects");
        return new UpwProjectsListPage(getWebDriver());
    }

    default DataMaintenancePage clickOnDataMaintenanceLink() {
        clickOnLinkViaText("Data Maintenance");
        return new DataMaintenancePage(getWebDriver());
    }

    default ReferenceDataPage clickOnReferenceDataLink() {
        clickOnLinkViaText("Reference Data");
        return new ReferenceDataPage(getWebDriver());
    }

    default CaseloadDiaryPage clickOnOfficerDiaryLink() {
        clickOnLinkViaText("Officer Diary");
        return new CaseloadDiaryPage(getWebDriver());
    }

    default CourtDiaryAppearancesPage clickOnCourtDiaryLink() {
        clickOnLinkViaText("Court Diary");
        return new CourtDiaryAppearancesPage(getWebDriver());
    }

    default ApprovedPremisesDiaryCurrentResidentsPage clickOnApprovedPremisesDiaryLink() {
        clickOnLinkViaText("Approved Premises Diary");
        return new ApprovedPremisesDiaryCurrentResidentsPage(getWebDriver());
    }

    default UserManagementPage clickOnUserAdministrationLink() {
        clickOnLinkViaText("User Administration");
        return new UserManagementPage(getWebDriver());
    }

    default MessageAdministrationPage clickOnMessageAdministrationLink() {
        clickOnLinkViaText("Message Administration");
        return new MessageAdministrationPage(getWebDriver());
    }

}
