ToDo list:

1. figure out how to read properties file for given environment 
   - goal is to be able to run login scenario on multiple environments
   
2. ability to configure and run tests on multiple threads (tests should be written so that they are independent) - DONE
   - configure and control thread numbers and browsers
mvn test - this will run all the tests - all should be passing except   Scenario: Create, update by CRN and Get an Event and Scenario: Create, update by event ID and Get an Event

3. ability to automatically assert each page header

4. Data creation - API - RestAssured  -- in progress @Richard
   1. e.g. login, create an offender, search offender, update offender  -- all done
   2. user data (usernames, passwords)  -- enums added
   3. reference data  -- added as JSON for now with method to update values, 
                      -- we may want to reconsider this as we get a feel for how much variation there is.
   4. Delete - no API endpoints yet - do we wait or use SQL??
   
5. Database query management  -- 
   - connection pool 
   - POC use db query in step assertion
   
6. Reporting  -- use cucumber??  I've enabled it but it needs improving on.

7. POC - smoke test added to this for higher env to run in Edge

8. Set default timeouts - I saw it wait 5 mins before timing out a failure to load the Home page

9. create a proper README.md to start building on. Include:
   1. java version
   2. how to run locally - with mvn and directly from intelliJ
