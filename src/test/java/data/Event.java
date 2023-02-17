package data;

import lombok.Getter;

import java.util.Map;

@Getter
public class Event {

    private Map<String, Object> eventResponseBody;

    private String eventId;
    private String mainOffence;
    private String offenceDate;
    private String referralDate;


    public void setEventDetails(Map<String, Object> eventResponseBody) {
        this.eventResponseBody = eventResponseBody;
        this.eventId = eventResponseBody.get("eventId").toString();
        this.mainOffence = eventResponseBody.get("mainOffenceCode").toString();
        this.offenceDate = eventResponseBody.get("mainOffenceDate").toString();
        this.referralDate = eventResponseBody.get("referralDate").toString();
    }

    public Event build(Map<String, Object> eventResponseBody) {
        setEventDetails(eventResponseBody);
        return this;
    }
}
