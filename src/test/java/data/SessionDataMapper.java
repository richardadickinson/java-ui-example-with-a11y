package data;

import testDataApi.Offender;

import java.util.Map;
import java.util.Objects;

import static stepDefinitions.BaseSteps.*;
import static testDataApi.Contact.insertContact;
import static testDataApi.Event.insertEvent;

public class SessionDataMapper {
    /**
     * Map Session Data for test data created for test scenarios
     */

    public static void createOffender(String path) {
        Map<String, Object> body = Offender.insertOffender(path);
        getOffenderSessionData().setTestOffenderDetails(Objects.requireNonNull(body));
    }

    public static void createEvent(String path) {
        Map<String, Object> testEventBody  = insertEvent(path, getOffenderSessionData().getCrn());
        getEventSessionData().setTestEventDetails(Objects.requireNonNull(testEventBody));
    }

    public static void createContact(String path){
        Map<String, Object> testContactBody = insertContact(path, getOffenderSessionData().getCrn());
        getContactSessionData().setTestContactDetails(testContactBody);
    }

}
