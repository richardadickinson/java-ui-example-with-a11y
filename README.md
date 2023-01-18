ToDo list:

1. figure out how to read properties file for given env config etc...
   1. goal is to be able to run login scenario on multiple: Done = able to run parallel except in Safari
   2. need to investigate Edge -- @Aurinelle - Done?
2. ability to run multiple threads (tests should be written so that they are independent)  -- Done
    check TestNG functions for this 
3. ability to automatically assert page header
4. Data creation - API - RestAssured  -- in progress @Richard
   1. e.g. login, create an offender, search offender, update offender  -- all done
   2. user data (usernames, passwords)  -- enums added
   3. reference data  -- added as JSON for now with method to update values, 
                      -- we may want to reconsider this as we get a feel for how much variation there is.
   4. Delete - no API endpoints yet - do we wait or use SQL??
5. Database query management  -- in progress @Aurinelle
6. Reporting  -- use cucumber??  I've enabled it but it needs improving on.
7. smoke test added to this for higher env to run in Edge - POC needed
8. Set default timeouts - I saw it wait 5 mins before timing out a failure to load the Home page
