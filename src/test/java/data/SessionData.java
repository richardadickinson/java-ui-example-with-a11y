package data;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Getter
public class SessionData {

    static Logger logger = LoggerFactory.getLogger(SessionData.class);

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
    private Map<String, Object> offenderResponseBody;

    public void setPersonDetails(Map<String, Object>  apiResponseBody) {
        this.offenderResponseBody = apiResponseBody;
        this.crn = apiResponseBody.get("crn").toString();
        this.title = apiResponseBody.get("titleCode").toString();;
        this.firstName = apiResponseBody.get("firstName").toString();;
        this.secondName = apiResponseBody.get("secondName").toString();;
        this.thirdName = apiResponseBody.get("thirdName").toString();;
        this.preferredName = apiResponseBody.get("preferredName").toString();;
        this.surname = apiResponseBody.get("surname").toString();;
        this.telephoneNumber = apiResponseBody.get("telephoneNumber").toString();;
        logger.debug("Test offender created with CRN: " + crn);
    }

    private String eventId;
    private Map<String, Object> eventResponseBody;
    
    public void setTestEventDetails(Map<String, Object>  apiResponseBody) {
        this.eventResponseBody = apiResponseBody;
        this.eventId = apiResponseBody.get("eventId").toString();
        logger.debug("Test event created with ID: " + eventId);
    }

    private String contactId;
    private Map<String, Object> contactResponseBody;

    public void setTestContactDetails(Map<String, Object> apiResponseBody) {
        this.contactResponseBody = apiResponseBody;
        this.contactId = apiResponseBody.get("contactID").toString();
        logger.debug("Test contact created with ID: " + contactId);
    }

}
