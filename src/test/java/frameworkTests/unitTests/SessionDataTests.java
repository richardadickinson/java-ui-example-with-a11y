package frameworkTests.unitTests;

import data.Contact;
import data.Event;
import data.Person;
import data.SessionData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static data.ContactDataTypes.CONTACT_ID;
import static data.ContactDataTypes.OFFENDER_CRN;
import static data.EventDataTypes.*;
import static data.PersonDataTypes.CRN;
import static data.PersonDataTypes.FULL_NAME;


public class SessionDataTests {

    private Map<String, Object> testPersonResponseBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("crn", "X123456");
        body.put("currentExclusion", "false");
        body.put("currentRestriction", "false");
        body.put("dateOfBirth", "2000-11-17T00:00:00Z[UTC]");
        body.put("ethnicityCode", "ETH01");
        body.put("firstName", "firstName");
        body.put("genderCode", "M");
        body.put("notes", "NOTES 123");
        body.put("preferredName", "PreferredName");
        body.put("secondName", "secondName");
        body.put("surname", "surname");
        body.put("telephoneNumber", "01234567890");
        body.put("thirdName", "thirdName");
        body.put("titleCode", "MR");
        return body;
    }

    private Map<String, Object> testContactResponseBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("contactID", "2505725097");
        body.put("area", "N07");
        body.put("contactDate", "2022-12-22T10:23:07.712Z[UTC]");
        body.put("contactType", "C378");
        body.put("notes", "notes");
        body.put("offenderCRN", "X123456");
        body.put("offenderId", "2500909250");
        body.put("staff", "C00P046");
        body.put("team", "DRHUAT");
        return body;
    }

    private Map<String, Object> testEventResponseBody() {
        Map<String, Object> body = new HashMap<>();
        body.put("eventId", "2500777638");
        body.put("convictionDate", "2022-12-05T00:00:00Z[UTC]");
        body.put("eventNumber", "1");
        body.put("mainOffenceCode", "00856");
        body.put("mainOffenceDate", "2022-11-22T00:00:00Z[UTC]");
        body.put("notes", "notes");
        body.put("offenderId", "2500909250");
        body.put("referralDate", "2022-11-23T00:00:00Z[UTC]");
        return body;
    }
    private Person person1, person2, person3;
    private Event event1 ,event2, event3;
    private Contact contact1, contact2, contact3;

    @Test
    public void testBuildPerson() {
        Person person = new Person().build(testPersonResponseBody());
        Assert.assertEquals(person.getPersonResponseBody(), testPersonResponseBody());
    }

    @Test
    public void testBuildEvent() {
        Event event = new Event().build(testEventResponseBody(), "xxx");
        Assert.assertEquals(event.getEventResponseBody(), testEventResponseBody());
    }

    @Test
    public void testBuildContact() {
        Contact contact = new Contact().build(testContactResponseBody());
        Assert.assertEquals(contact.getContactResponseBody(), testContactResponseBody());
    }

    @Test
    public void testConvertGenderMale() {
        Map<String, Object> body = testPersonResponseBody();
        SessionData sessionData = new SessionData();
        Person person = new Person().build(body);
        sessionData.setPerson(person);
        Assert.assertEquals(person.getGender(), "Male");
    }

    @Test
    public void testConvertGenderFemale() {
        Map<String, Object> body = testPersonResponseBody();
        body.replace("genderCode", "F");
        SessionData sessionData = new SessionData();
        Person person = new Person().build(body);
        sessionData.setPerson(person);
        Assert.assertEquals(person.getGender(), "Female");
    }

    @Test
    public void testConvertGenderOther() {
        Map<String, Object> body = testPersonResponseBody();
        body.replace("genderCode", "N");
        SessionData sessionData = new SessionData();
        Person person = new Person().build(body);
        sessionData.setPerson(person);
        Assert.assertEquals(person.getGender(), "N");
    }

    @Test
    public void testSetAndGetPerson() {
        SessionData sessionData = new SessionData();
        Person person = new Person().build(testPersonResponseBody());
        sessionData.setPerson(person);
        Assert.assertEquals(person, sessionData.getPerson());
        Assert.assertEquals(person, sessionData.getPersons().get(0));
    }

    @Test
    public void testSetAndGetEvent() {
        SessionData sessionData = new SessionData();
        Event event = new Event().build(testEventResponseBody(), "xxx");
        sessionData.setEvent(event);
        Assert.assertEquals(event, sessionData.getEvent());
        Assert.assertEquals(event, sessionData.getEvents().get(0));
    }

    @Test
    public void testSetAndGetContact() {
        SessionData sessionData = new SessionData();
        Contact contact = new Contact().build(testContactResponseBody());
        sessionData.setContact(contact);
        Assert.assertEquals(contact, sessionData.getContact());
        Assert.assertEquals(contact, sessionData.getContacts().get(0));
    }

    @Test
    public void testSetAndGetMultiplePersons() {
        SessionData sessionData = buildTestSessionDataForMultiplePersons();
        Assert.assertEquals(person1, sessionData.getPersons().get(0));
        Assert.assertEquals(person2, sessionData.getPersons().get(1));
        Assert.assertEquals(person3, sessionData.getPersons().get(2));
    }

    @Test
    public void testSetAndGetMultipleEvents() {
        SessionData sessionData = buildTestSessionDataForMultipleEvents();
        Assert.assertEquals(event1, sessionData.getEvents().get(0));
        Assert.assertEquals(event2, sessionData.getEvents().get(1));
        Assert.assertEquals(event3, sessionData.getEvents().get(2));
    }
    @Test
    public void testSetAndGetMultipleContacts() {
        SessionData sessionData = buildTestSessionDataForMultipleContacts();
        Assert.assertEquals(contact1, sessionData.getContacts().get(0));
        Assert.assertEquals(contact2, sessionData.getContacts().get(1));
        Assert.assertEquals(contact3, sessionData.getContacts().get(2));
    }

    @Test
    public void testGetPersonFromArrayByCRN() {
        SessionData sessionData = buildTestSessionDataForMultiplePersons();
        Person fetchedPerson = sessionData.getPersonByValueFromPersons(CRN, "X234567");
        Assert.assertEquals(person2, fetchedPerson);
    }
    @Test
    public void testGetPersonFromArrayByName() {
        SessionData sessionData = buildTestSessionDataForMultiplePersons();
        Person fetchedPerson = sessionData.getPersonByValueFromPersons(FULL_NAME, "firstName findme");
        Assert.assertEquals(person3, fetchedPerson);
    }

    @Test
    public void testGetEventFromArrayByEventId() {
        SessionData sessionData = buildTestSessionDataForMultipleEvents();
        Event fetchedEvent = sessionData.getEventByValueFromEvents(EVENT_ID, "2505725101");
        Assert.assertEquals(event3, fetchedEvent);
    }
    @Test
    public void testGetEventFromArrayByOffenderId() {
        SessionData sessionData = buildTestSessionDataForMultipleEvents();
        Event fetchedEvent = sessionData.getEventByValueFromEvents(OFFENDER_ID, "2500909111");
        Assert.assertEquals(event2, fetchedEvent);
    }
    @Test
    public void testGetEventFromArrayByPersonCrn() {
        SessionData sessionData = buildTestSessionDataForMultipleEvents();
        Event event = sessionData.getEventByValueFromEvents(PERSON_CRN, "xxx3");
        Assert.assertEquals(event3, event);
    }
    @Test
    public void testGetContactFromArrayByContactID() {
        SessionData sessionData = buildTestSessionDataForMultipleContacts();
        Contact fetchedContact = sessionData.getContactByValueFromContacts(CONTACT_ID, "2505725100");
        Assert.assertEquals(contact2, fetchedContact);
    }
    @Test
    public void testGetContactFromArrayByOffenderCRN() {
        SessionData sessionData = buildTestSessionDataForMultipleContacts();
        Contact fetchedContact = sessionData.getContactByValueFromContacts(OFFENDER_CRN, "X345678");
        Assert.assertEquals(contact3, fetchedContact);
    }
    @Test
    public void testGetPersonFromArrayByIteratorReturnsNullWhenPersonNotFound() {
        SessionData sessionData = buildTestSessionDataForMultiplePersons();
        Person nullPerson = sessionData.getPersonByValueFromPersons(CRN, "rhubarb");
        Assert.assertNull(nullPerson);
    }

    @Test
    public void testGetEventFromArrayByIteratorReturnsNullWhenEventNotFound() {
        SessionData sessionData = buildTestSessionDataForMultipleEvents();
        Event nullEvent = sessionData.getEventByValueFromEvents(EVENT_ID, "rhubarb");
        Assert.assertNull(nullEvent);
    }

    @Test
    public void testGetContactFromArrayByIteratorReturnsNullWhenContactNotFound() {
        SessionData sessionData = buildTestSessionDataForMultipleContacts();
        Contact nullContact = sessionData.getContactByValueFromContacts(CONTACT_ID, "rhubarb");
        Assert.assertNull(nullContact);
    }

    private SessionData buildTestSessionDataForMultiplePersons() {
        SessionData sessionData = new SessionData();
        Map<String, Object> body = testPersonResponseBody();
        person1 = new Person().build(body);
        body.replace("crn", "X234567");
        person2 = new Person().build(body);
        body.replace("crn", "X345678");
        body.replace("surname", "findme");
        person3 = new Person().build(body);
        sessionData.setPerson(person1);
        sessionData.setPerson(person2);
        sessionData.setPerson(person3);
        return sessionData;
    }
    private SessionData buildTestSessionDataForMultipleEvents() {
        SessionData sessionData = new SessionData();
        Map<String, Object> body = testEventResponseBody();
        event1 = new Event().build(body, "xxx1");
        body.replace("eventId", "2505725100");
        body.replace("offenderId", "2500909111");
        event2 = new Event().build(body, "xxx2");
        body.replace("eventId", "2505725101");
        body.replace("offenderId", "2500909222");
        event3 = new Event().build(body, "xxx3");
        sessionData.setEvent(event1);
        sessionData.setEvent(event2);
        sessionData.setEvent(event3);
        return sessionData;
    }
    private SessionData buildTestSessionDataForMultipleContacts() {
        SessionData sessionData = new SessionData();
        Map<String, Object> body = testContactResponseBody();
        contact1 = new Contact().build(body);
        body.replace("contactID", "2505725100");
        body.replace("offenderCRN", "X234567");
        contact2 = new Contact().build(body);
        body.replace("contactID", "2505725101");
        body.replace("offenderCRN", "X345678");
        contact3 = new Contact().build(body);
        sessionData.setContact(contact1);
        sessionData.setContact(contact2);
        sessionData.setContact(contact3);
        return sessionData;
    }
}