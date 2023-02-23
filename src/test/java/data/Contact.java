package data;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static utils.DateUtil.convertApiDate;

@Getter
public class Contact {

    private static Logger logger = LoggerFactory.getLogger(Contact.class);

    private String contactDate;
    private String contactId;
    private String contactType;
    private String offenderCRN;
    private Map<String, Object> contactResponseBody;


    public Contact build(Map<String, Object> contactResponseBody) {
        this.contactResponseBody = contactResponseBody;
        this.contactDate = convertApiDate(contactResponseBody.get("contactDate").toString());
        this.contactId = contactResponseBody.get("contactID").toString();
        this.contactType = contactResponseBody.get("contactType").toString();
        this.offenderCRN = contactResponseBody.get("offenderCRN").toString();
        return this;
    }
}
