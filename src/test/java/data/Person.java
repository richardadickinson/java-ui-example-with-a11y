package data;

import lombok.Getter;

import java.util.Map;

@Getter
public class Person {

    private Map<String, Object> personResponseBody;

    private String crn;
    private String title;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String preferredName;
    private String surname;
    private String sex;
    private String dateOfBirth;
    private String telephoneNumber;

    public void setPersonDetails(Map<String, Object> personResponseBody) {
        this.personResponseBody = personResponseBody;
        this.crn = personResponseBody.get("crn").toString();
        this.title = personResponseBody.get("titleCode").toString();
        this.firstName = personResponseBody.get("firstName").toString();
        this.secondName = personResponseBody.get("secondName").toString();
        this.thirdName = personResponseBody.get("thirdName").toString();
        this.preferredName = personResponseBody.get("preferredName").toString();
        this.surname = personResponseBody.get("surname").toString();
        this.telephoneNumber = personResponseBody.get("telephoneNumber").toString();
    }

    public Person build(Map<String, Object> personResponseBody) {
        setPersonDetails(personResponseBody);
        return this;
    }

}
