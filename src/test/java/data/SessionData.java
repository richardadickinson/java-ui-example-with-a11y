package data;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@Getter
public class SessionData {

    private static final Logger logger = LoggerFactory.getLogger(SessionData.class);

    private final ArrayList<Person> persons;

    private Person person;
    private Event event;
    private Contact contact;

    public SessionData() {
        this.persons = new ArrayList<>();
    }

    public void setPerson(Person person) {
        this.person = person;
        this.persons.add(this.person);
        logger.info("Test person created with CRN: " + person.getCrn());
    }

    public void setEvent(Event event) {
        this.event = event;
        logger.debug("Test event created with ID: " + event.getEventId());
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        logger.debug("Test contact created with ID: " + contact.getContactId());
    }

    public Person getPersonByValueFromPersons(String match, String value) {
        for (Person p : getPersons()) {
            switch(match) {
                case "crn":
                    logger.debug("Retrieved CRN " + p.getCrn());
                    if (p.getCrn().equalsIgnoreCase(value))
                        return p;
            }
        }
        return null;
    }

}
