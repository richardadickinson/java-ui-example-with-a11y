package data;

import lombok.Getter;

import java.util.Map;

import static utils.DateUtil.convertApiDate;

@Getter
public class Person {

    private String crn;
    private String title;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String preferredName;
    private String surname;
    private String gender;
    private String dateOfBirth;
    private String telephoneNumber;
    private Map<String, Object> personResponseBody;

    private static String convertGender(String apiGender) {
        if (apiGender.equals("M")) {
            return "Male";
        } else if (apiGender.equals("F")) {
            return "Female";
        }
        return apiGender;
    }

    public Person build(Map<String, Object> personResponseBody) {
        this.personResponseBody = personResponseBody;
        this.crn = personResponseBody.get("crn").toString();
        this.title = personResponseBody.get("titleCode").toString();
        this.firstName = personResponseBody.get("firstName").toString();
        this.secondName = personResponseBody.get("secondName").toString();
        this.thirdName = personResponseBody.get("thirdName").toString();
        this.preferredName = personResponseBody.get("preferredName").toString();
        this.surname = personResponseBody.get("surname").toString();
        this.gender = convertGender(personResponseBody.get("genderCode").toString());
        this.dateOfBirth = convertApiDate(personResponseBody.get("dateOfBirth").toString());
        this.telephoneNumber = personResponseBody.get("telephoneNumber").toString();
        return this;
    }

}
