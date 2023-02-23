package data;

import lombok.Getter;

import java.util.Map;

import static utils.DateUtil.convertApiDate;

@Getter
public class Event {

    private String convictionDate;
    private String eventId;
    private String mainOffenceCode;
    private String mainOffenceDate;
    private String notes;
    private String referralDate;
    private Map<String, Object> eventResponseBody;

    public Event build(Map<String, Object> eventResponseBody) {
        this.eventResponseBody = eventResponseBody;
        this.convictionDate = convertApiDate(eventResponseBody.get("convictionDate").toString());
        this.eventId = eventResponseBody.get("eventId").toString();
        this.mainOffenceCode = eventResponseBody.get("mainOffenceCode").toString();
        this.mainOffenceDate = convertApiDate(eventResponseBody.get("mainOffenceDate").toString());
        this.notes = eventResponseBody.get("notes").toString();
        this.referralDate = convertApiDate(eventResponseBody.get("referralDate").toString());
        return this;
    }
}
