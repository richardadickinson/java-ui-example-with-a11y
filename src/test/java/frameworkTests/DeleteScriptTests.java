package frameworkTests;

import data.Person;
import data.SessionData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import testDataApi.Offender;

import java.util.Map;

import static config.TestDataApiConfig.apiRequestPath;
import static utils.dbUtils.DeleteScript.*;

public class DeleteScriptTests {

    private SessionData sessionData = new SessionData();


    @Test
    public void testSingleCrnCanBeDeleted() {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        assert body != null;
        String crn = (String) body.get("crn");
        deleteOffenderByCRN(crn);
        Assert.assertEquals(crnExists(crn), false);
    }

    @Test
    public void testCrnDoesNotExist() {
        Assert.assertEquals(crnExists("0098765"), false);
    }

    @Test
    public void testCrnExists() {
        Map<String, Object> body = Offender.insertOffender(apiRequestPath + "create-offender.json");
        assert body != null;
        sessionData.setPerson(new Person().build(body));
        Assert.assertEquals(crnExists(body.get("crn").toString()), true);
    }

    @AfterMethod
    public void deleteCrn() {
        if (sessionData.getPersons()!=null){
            deleteOffendersByPerson(sessionData.getPersons());
        }

    }

}
