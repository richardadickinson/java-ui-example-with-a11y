package data;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Getter
public class Contact {

    private static Logger logger = LoggerFactory.getLogger(Contact.class);

    private String contactId;
    private String contactType;

    public Contact build(Map<String, Object> contactResponseBody) {
        this.contactId = contactResponseBody.get("contactID").toString();
        this.contactType = contactResponseBody.get("contactType").toString();
        return this;
    }
}
