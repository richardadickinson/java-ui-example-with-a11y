package config;

public enum Users {

    API_LOGIN_USER("test1", "Password1", "test1", "surname1"),
    UMT_ADMIN("test2", "Password1", "test2", "surname2"),
    PERF_USER("perf1", "Password1", "Performance", "Test");

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

