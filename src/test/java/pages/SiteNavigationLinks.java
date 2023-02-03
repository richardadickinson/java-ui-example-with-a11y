package pages;

public enum SiteNavigationLinks {

    NATIONAL_SEARCH_LINK_TEXT("National Search"),
    NATIONAL_CUSTODY_SEARCH_LINK_TEXT("National Custody Search"),
    RECENTLY_VIEWED_LINK_TEXT("Recently Viewed"),
    UPW_PROJECT_DIARY_LINK_TEXT("UPW Project Diary"),
    UPW_PROJECT_LINK_TEXT("UPW Project"),
    DATA_MAINTENANCE_LINK_TEXT("Data Maintenance"),
    REFERENCE_DATA_LINK_TEXT("Reference Data"),
    OFFICER_DIARY_LINK_TEXT("Officer Diary"),
    COURT_DIARY_LINK_TEXT("Court Diary"),
    APPROVED_PREMISES_DIARY_LINK_TEXT("Approved Premises Diary"),
    USER_PREFERENCES("User Preferences"),
    SIGN_OUT_LINK_TEXT("Sign Out"),
    USER_ADMINISTRATION_LINK_TEXT("User Administration"),
    MESSAGE_ADMINISTRATION_LINK_TEXT("Message Administration"),
    NEW_SEARCH_LINK_TEXT("New Search"),
    CASE_MANAGEMENT_LINK_TEXT("Case Management"),
    HOME_PAGE_LINK_TEXT("Home");
    private final String navigationLinkName;

    SiteNavigationLinks(String navigationLinkName) {
        this.navigationLinkName = navigationLinkName;
    }

    public String getNavigationLinkName() {
        return navigationLinkName;
    }
}
