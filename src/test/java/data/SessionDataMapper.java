package data;

import testDataApi.Offender;

import java.util.Map;
import java.util.Objects;

import static stepDefinitions.BaseSteps.getSessionData;
import static testDataApi.Contact.insertContact;
import static testDataApi.Event.insertEvent;

public class SessionDataMapper {
    /**
     * Map Session Data for test data created for test scenarios
     */

    public static void createPerson(String path) {
        Map<String, Object> body = Offender.insertOffender(path);
        Person person = new Person().build(Objects.requireNonNull(body));
        getSessionData().setPerson(person);
    }

    public static void createMultiplePersons(String path, int numberOfPersons) {
        for (int i = 1; i <= numberOfPersons; i++) {
            Map<String, Object> body = Offender.insertOffender(path);
            Person person = new Person().build(Objects.requireNonNull(body));
            getSessionData().setPerson(person);
        }
    }

    public static void createEvent(String path) { // ToDo: this will need to take crn else won't work when multiple persons exist
        String crn = getSessionData().getPerson().getCrn();
        Map<String, Object> body  = insertEvent(path, crn);
        Event event = new Event().build(Objects.requireNonNull(body), crn);
        getSessionData().setEvent(event);
    }

    public static void createContact(String path){
        Map<String, Object> body = insertContact(path, getSessionData().getPerson().getCrn());
        Contact contact = new Contact().build(Objects.requireNonNull(body));
        getSessionData().setContact(contact);
    }

}
