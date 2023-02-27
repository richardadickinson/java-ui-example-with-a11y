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
    public void testMultiplePersonsCanBeDeleted() {
        Map<String, Object> body1 = Offender.insertOffender(apiRequestPath + "create-offender.json");
        sessionData.setPerson(new Person().build(body1));
        Map<String, Object> body2 = Offender.insertOffender(apiRequestPath + "create-offender.json");
        sessionData.setPerson(new Person().build(body2));
        Map<String, Object> body3 = Offender.insertOffender(apiRequestPath + "create-offender.json");
        sessionData.setPerson(new Person().build(body3));
        Map<String, Object> body4 = Offender.insertOffender(apiRequestPath + "create-offender.json");
        sessionData.setPerson(new Person().build(body4));
        deleteOffendersByPerson(sessionData.getPersons());
        Assert.assertEquals(crnExists(sessionData.getPersons().get(0).getCrn()), false);
        Assert.assertEquals(crnExists(sessionData.getPersons().get(1).getCrn()), false);
        Assert.assertEquals(crnExists(sessionData.getPersons().get(2).getCrn()), false);
        Assert.assertEquals(crnExists(sessionData.getPersons().get(3).getCrn()), false);
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
