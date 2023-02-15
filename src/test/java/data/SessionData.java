package data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SessionData {

    static Logger logger = LoggerFactory.getLogger(SessionData.class);
    private OffenderData offenderData;
    private EventData eventData;
    private ContactData contactData;

    public OffenderData getOffenderData() {
        return offenderData;
    }

    public OffenderData setOffenderData(OffenderData offenderData) {
        this.offenderData = offenderData;
        return offenderData;
    }

    public EventData getEventData() {
        return eventData;
    }

    public EventData setEventData(EventData eventData) {
        this.eventData = eventData;
        return eventData;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public ContactData setContactData(ContactData contactData) {
        this.contactData = contactData;
        return contactData;
    }

    public static class OffenderData {
        private String crn;
        private String firstName;
        private String surname;
        private Map<String, Object> offenderResponseBody;

        public String getCrn() {
            return crn;
        }
        public String getFirstName() {
            return firstName;
        }
        public String getSurname() {
            return surname;
        }

        public Map<String, Object> getTestOffenderDetails() {
            return offenderResponseBody;
        }

        public void setTestOffenderDetails(Map<String, Object>  apiResponseBody) {
            this.offenderResponseBody = apiResponseBody;
            this.crn = (String) apiResponseBody.get("crn");
            this.firstName = (String) apiResponseBody.get("firstName");
            this.surname = (String) apiResponseBody.get("surname");
            logger.debug("Test offender created with CRN: " + crn);
        }
    }

    public static class EventData{
        private String eventId;
        private Map<String, Object> eventResponseBody;

        public String getEventId() {
            return eventId;
        }

        public Map<String, Object> getTestEventDetails() {
            return eventResponseBody;
        }

        public void setTestEventDetails(Map<String, Object> apiResponseBody) {
            this.eventResponseBody = apiResponseBody;
            this.eventId = apiResponseBody.get("eventId").toString();
            logger.debug("Test event created with ID: " + eventId);
        }
    }

    public static class ContactData{
        private String contactId;
        private Map<String, Object> contactResponseBody;

        public String getContactId() {
            return contactId;
        }
        public Map<String, Object> getTestContactDetails() {
            return contactResponseBody;
        }

        public void setTestContactDetails(Map<String, Object> apiResponseBody) {
            this.contactResponseBody = apiResponseBody;
            this.contactId = apiResponseBody.get("contactID").toString();
            logger.debug("Test contact created with ID: " + contactId);
        }
    }

}
