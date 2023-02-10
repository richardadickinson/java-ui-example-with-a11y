package pages;

import org.openqa.selenium.WebDriver;
import pages.approvedPremisesDiary.ApprovedPremisesDiaryCurrentResidentsPage;
import pages.courtDiary.CourtDiaryAppearancesPage;
import pages.messageAdministration.MessageAdministrationPage;
import pages.officerDiary.CaseloadDiaryPage;

import static utils.webDriver.Builder.getWebDriver;

public class HomePage extends BasePageObject {

    private static final String expectedPageTitle = "National Delius Home Page";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        assertPageTitle(expectedPageTitle);
    }

    public NationalSearchPage clickOnNationalSearch() {
        clickOnLinkViaText("National Search");
        return new NationalSearchPage(getWebDriver());
    }

    public NationalCustodySearchPage clickOnNationalCustodySearch() {
        clickOnLinkViaText("National Custody Search");
        return new NationalCustodySearchPage(getWebDriver());
    }

    public RecentlyViewedPage clickOnRecentlyViewed() {
        clickOnLinkViaText("Recently Viewed");
        return new RecentlyViewedPage(getWebDriver());
    }

    public UpwProjectDiaryPage clickOnUpwProjectDiary() {
        clickOnLinkViaText("UPW Project Diary");
        return new UpwProjectDiaryPage(getWebDriver());
    }

    public UpwProjectsListPage clickOnUpwProjects() {
        clickOnLinkViaText("UPW Projects");
        return new UpwProjectsListPage(getWebDriver());
    }

    public DataMaintenancePage clickOnDataMaintenance() {
        clickOnLinkViaText("Data Maintenance");
        return new DataMaintenancePage(getWebDriver());
    }

    public ReferenceDataPage clickOnReferenceData() {
        clickOnLinkViaText("Reference Data");
        return new ReferenceDataPage(getWebDriver());
    }

    public CaseloadDiaryPage clickOnOfficerDiary() {
        clickOnLinkViaText("Officer Diary");
        return new CaseloadDiaryPage(getWebDriver());
    }

    public CourtDiaryAppearancesPage clickOnCourtDiary() {
        clickOnLinkViaText("Court Diary");
        return new CourtDiaryAppearancesPage(getWebDriver());
    }

    public ApprovedPremisesDiaryCurrentResidentsPage clickOnCApprovedPremisesDiary() {
        clickOnLinkViaText("Approved Premises Diary");
        return new ApprovedPremisesDiaryCurrentResidentsPage(getWebDriver());
    }

    public UserManagementPage clickOnUserAdministration() {
        clickOnLinkViaText("User Administration");
        return new UserManagementPage(getWebDriver());
    }

    public MessageAdministrationPage clickOnMessageAdministration() {
        clickOnLinkViaText("Message Administration");
        return new MessageAdministrationPage(getWebDriver());
    }

}
