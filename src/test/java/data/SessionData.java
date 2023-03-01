package data;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@Getter
public class SessionData {

    private static final Logger logger = LoggerFactory.getLogger(SessionData.class);

    private final ArrayList<Person> persons;
    private final ArrayList<Event> events;
    private final ArrayList<Contact> contacts;

    private Person person;
    private Event event;
    private Contact contact;

    public SessionData() {
        this.persons = new ArrayList<>();
        this.events = new ArrayList<>();
        this.contacts = new ArrayList<>();
    }

    public void setPerson(Person person) {
        this.person = person;
        this.persons.add(this.person);
        logger.info("Test person created with CRN: " + person.getCrn());
    }

    public void setEvent(Event event) {
        this.event = event;
        this.events.add(this.event);
        logger.info("Test event created with ID: " + event.getEventId());
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        this.contacts.add(this.contact);
        logger.info("Test contact created with ID: " + contact.getContactId());
    }

    public Person getPersonByValueFromPersons(PersonDataTypes match, String value) {
        for (Person p : getPersons()) {
            switch (match) {
                case CRN -> {
                    if (p.getCrn().equalsIgnoreCase(value)) {
                        logger.debug("Retrieved person by CRN " + value);
                        return p;
                    }
                }
                case FULL_NAME -> {
                    String name = p.getFirstName() + " " + p.getSurname();
                    if (name.equalsIgnoreCase(value)) {
                        logger.debug("Retrieved person by name: " + value);
                        return p;
                    }
                }
            }
        }
        logger.debug("No match by " + match + " (" + value + ") found; returning null");
        return null;
    }

    public Contact getContactByValueFromContacts(ContactDataTypes match, String value) {
        for (Contact c : getContacts()) {
            switch (match) {
                case CONTACT_ID -> {
                    if (c.getContactId().equals(value)) {
                        logger.debug("Retrieved contact by contactID: " + value);
                        return c;
                    }
                }
                case OFFENDER_CRN -> {
                    if (c.getOffenderCRN().equalsIgnoreCase(value)) {
                        logger.debug("Retrieved contact by offenderCRN: " + value);
                        return c;
                    }
                }
            }
        }
        logger.debug("No match by " + match + " (" + value + ") found; returning null");
        return null;
    }

    public Event getEventByValueFromEvents(EventDataTypes match, String value) {
        for (Event e : getEvents()) {
            switch (match) {
                case EVENT_ID -> {
                    if (e.getEventId().equals(value)) {
                        logger.debug("Retrieved event by eventId: {}", value);
                        return e;
                    }
                }
                case OFFENDER_ID -> {
                    if (e.getOffenderId().equals(value)) {
                        logger.debug("Retrieved event by offenderId: {}", value);
                        return e;
                    }
                }
                case PERSON_CRN -> {
                    if (e.getPersonCrn().equalsIgnoreCase(value)) {
                        logger.debug("Retrieved event by person CRN: {}", value);
                        return e;
                    }
                }
            }
        }
        logger.debug("No match by " + match + " (" + value + ") found; returning null");
        return null;
    }

}
