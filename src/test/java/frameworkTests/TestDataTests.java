package frameworkTests;

import data.Contact;
import data.Event;
import data.Person;
import data.SessionData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestDataTests {

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
        body.put("area", "N07");
        body.put("contactDate", "2022-12-22T10:23:07.712Z[UTC]");
        body.put("contactID", "2505725097");
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
        body.put("convictionDate", "2022-12-05T00:00:00Z[UTC]");
        body.put("eventId", "2500777638");
        body.put("eventNumber", "1");
        body.put("mainOffenceCode", "00856");
        body.put("mainOffenceDate", "2022-11-22T00:00:00Z[UTC]");
        body.put("notes", "notes");
        body.put("offenderId", "2500909250");
        body.put("referralDate", "2022-11-23T00:00:00Z[UTC]");
        return body;
    }

    @Test
    public void testBuildPerson() {
        Person person = new Person().build(testPersonResponseBody());
        Assert.assertEquals(person.getPersonResponseBody(), testPersonResponseBody());
    }

    @Test
    public void testBuildEvent() {
        Event event = new Event().build(testEventResponseBody());
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
        body.remove("genderCode");
        body.put("genderCode", "F");
        SessionData sessionData = new SessionData();
        Person person = new Person().build(body);
        sessionData.setPerson(person);
        Assert.assertEquals(person.getGender(), "Female");
    }

    @Test
    public void testConvertGenderOther() {
        Map<String, Object> body = testPersonResponseBody();
        body.remove("genderCode");
        body.put("genderCode", "N");
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
    }

    @Test
    public void testSetAndGetEvent() {
        SessionData sessionData = new SessionData();
        Event event = new Event().build(testEventResponseBody());
        sessionData.setEvent(event);
        Assert.assertEquals(event, sessionData.getEvent());
    }

    @Test
    public void testSetAndGetContact() {
        SessionData sessionData = new SessionData();
        Contact contact = new Contact().build(testContactResponseBody());
        sessionData.setContact(contact);
        Assert.assertEquals(contact, sessionData.getContact());
    }
}