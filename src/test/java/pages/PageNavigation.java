package pages;

public enum PageNavigation {

    NATIONAL_SEARCH("National Search", "National Search"),
    NATIONAL_CUSTODY_SEARCH("National Custody Search", "National Custody Search"),
    RECENTLY_VIEWED("Recently Viewed", "Recently Viewed Records"),
    UPW_PROJECT_DIARY("UPW Project Diary", "UPW Project Diary"),
    UPW_PROJECT("UPW Project", "UPW Projects List"),
    DATA_MAINTENANCE("Data Maintenance", "Data Maintenance"),
    REFERENCE_DATA("Reference Data", "Reference Data"),
    OFFICER_DIARY("Officer Diary", "Caseload Diary"),
    COURT_DIARY("Court Diary", "Court Diary - Appearances"),
    APPROVED_PREMISES_DIARY("Approved Premises Diary", "Approved Premises Diary - Current Residents"),
    USER_PREFERENCES("User Preferences", "User Preferences"),
    SIGN_OUT("Sign Out", "Exit NDelius"),
    USER_ADMINISTRATION("User Administration", "User Management"),
    MESSAGE_ADMINISTRATION("Message Administration", "Message Administration"),
    NEW_SEARCH("New Search", "National Search"),
    CASE_MANAGEMENT("Case Management", "Case Management"),
    HOME_PAGE("Home", "National Delius Home Page"),
    LOGIN_PAGE("Log in", "National Delius - Login");

    private final String linkName;
    private final String pageTitle;

    PageNavigation(String linkName, String pageTitle) {
        this.linkName = linkName;
        this.pageTitle = pageTitle;
    }

    public String getLinkName() {
        return linkName;
    }

    public String getPageTitle() {
        return pageTitle;
    }
}
