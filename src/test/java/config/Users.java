package config;

public enum Users {

    API_LOGIN_USER("NDelius30", "Password1", "nDelius30", "nDelius30"),
    UMT_ADMIN("NDelius21", "Password1", "ndelius21", "ndelius21");

    private final String firstName;
    private final String password;
    private final String surname;
    private final String username;

    Users(String username, String password, String firstName, String surname) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getSurname() {
        return surname;
    }

}

