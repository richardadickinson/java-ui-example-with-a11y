package pages;

import org.openqa.selenium.WebDriver;
import pages.approvedPremisesDiary.ApprovedPremisesDiaryCurrentResidentsPage;
import pages.courtDiary.CourtDiaryAppearancesPage;
import pages.messageAdministration.MessageAdministrationPage;
import pages.officerDiary.CaseloadDiaryPage;

public class HomePage extends BasePageObject {

    private static final String expectedPageTitle = "National Delius Home Page";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public NationalSearchPage clickOnNationalSearch() {
        clickOnLinkViaText("National Search");
        return new NationalSearchPage(webDriver);
    }

    public NationalCustodySearchPage clickOnNationalCustodySearch() {
        clickOnLinkViaText("National Custody Search");
        return new NationalCustodySearchPage(webDriver);
    }

    public RecentlyViewedPage clickOnRecentlyViewed() {
        clickOnLinkViaText("Recently Viewed");
        return new RecentlyViewedPage(webDriver);
    }

    public UpwProjectDiaryPage clickOnUpwProjectDiary() {
        clickOnLinkViaText("UPW Project Diary");
        return new UpwProjectDiaryPage(webDriver);
    }

    public UpwProjectsListPage clickOnUpwProjects() {
        clickOnLinkViaText("UPW Projects");
        return new UpwProjectsListPage(webDriver);
    }

    public DataMaintenancePage clickOnDataMaintenance() {
        clickOnLinkViaText("Data Maintenance");
        return new DataMaintenancePage(webDriver);
    }

    public ReferenceDataPage clickOnReferenceData() {
        clickOnLinkViaText("Reference Data");
        return new ReferenceDataPage(webDriver);
    }

    public CaseloadDiaryPage clickOnOfficerDiary() {
        clickOnLinkViaText("Officer Diary");
        return new CaseloadDiaryPage(webDriver);
    }

    public CourtDiaryAppearancesPage clickOnCourtDiary() {
        clickOnLinkViaText("Court Diary");
        return new CourtDiaryAppearancesPage(webDriver);
    }

    public ApprovedPremisesDiaryCurrentResidentsPage clickOnCApprovedPremisesDiary() {
        clickOnLinkViaText("Approved Premises Diary");
        return new ApprovedPremisesDiaryCurrentResidentsPage(webDriver);
    }

    public UserManagementPage clickOnUserAdministration() {
        clickOnLinkViaText("User Administration");
        return new UserManagementPage(webDriver);
    }

    public MessageAdministrationPage clickOnMessageAdministration() {
        clickOnLinkViaText("Message Administration");
        return new MessageAdministrationPage(webDriver);
    }


}
