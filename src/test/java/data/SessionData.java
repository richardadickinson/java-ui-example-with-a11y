package data;

import java.util.Map;

public class SessionData {
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
        private String offenderFirstName;
        private String offenderSurname;
        private Map<String, Object> apiResponseBody;

        public String getCrn() {
            return crn;
        }

        public void setCrn(String crn) {
            this.crn = crn;
        }

        public String getOffenderSurname() {
            return offenderSurname;
        }

        public void setOffenderSurname(String offenderSurname) {
            this.offenderSurname = offenderSurname;
        }

        public Map<String, Object>  getApiResponseBody() {
            return apiResponseBody;
        }

        public void setApiResponseBody(Map<String, Object>  apiResponseBody) {
            this.apiResponseBody = apiResponseBody;
        }

        public String getOffenderFirstName() {
            return offenderFirstName;
        }

        public void setOffenderFirstName(String offenderFirstName) {
            this.offenderFirstName = offenderFirstName;
        }
    }

    public static class EventData{
        private String eventId;
        private Map<String, Object> apiResponseBody;

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }

        public Map<String, Object> getApiResponseBody() {
            return apiResponseBody;
        }

        public void setApiResponseBody(Map<String, Object> apiResponseBody) {
            this.apiResponseBody = apiResponseBody;
        }
    }

    public static class ContactData{
        private String contactId;
        private Map<String, Object> apiResponseBody;

        public String getContactId() {
            return contactId;
        }
        public void setContactId(String contactId) {
            this.contactId = contactId;
        }
        public Map<String, Object> getApiResponseBody() {
            return apiResponseBody;
        }
        public void setApiResponseBody(Map<String, Object> apiResponseBody) {
            this.apiResponseBody = apiResponseBody;
        }
    }


}
