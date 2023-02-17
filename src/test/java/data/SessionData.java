package data;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@Getter
public class SessionData {

    private static Logger logger = LoggerFactory.getLogger(SessionData.class);

    private ArrayList<Person> persons; // ToDo: create a method to return an Array of Person based on int param

    private Person person;
    private Event event;
    private Contact contact;

    public void setPerson(Person person) {
        this.person = person;
        logger.info("Test person created with ID: " + person.getCrn());
    }

    public void setEvent(Event event) {
        this.event = event;
        logger.debug("Test event created with ID: " + event.getEventId());
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        logger.debug("Test contact created with ID: " + contact.getContactId());
    }

}
