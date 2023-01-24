ToDo list:

1. figure out how to read properties file for given environment  - DONE
   
2. ability to configure and run tests on multiple threads (tests should be written so that they are independent) - DONE

3. ability to automatically assert each page header - screenplay?

4. Data management
   1. Data creation (Offender, Event, Contacts)
      1. API - RestAssured -- in progress @Richard
         1. e.g. login, create an offender, search offender, update offender - ISSUE: Event endpoint
         3. Delete - no API endpoints yet - do we wait or use SQL??
         4. Breakdown of JSON quest for each endpoint - e.g. "mainOffenceCode": "00856" - where is the value coming from? book time with devs to understand
         5. API Request JSON management - do we maintain specific JSONs - what about data randomisation?
      2. Reference Data - do we need to create specific ones just for automation pack? if so, how will we do that? sql injections?
      3. user data (usernames, passwords)  -- enums added - DONE
   
6. Database query management  -- DONE
   - connection pool 
   - POC use db query in step assertion
   
7. Reporting  -- use cucumber??  I've enabled it but it needs improving on. - ongoing

8. POC - smoke test added to this for higher env to run in Edge - not started

9. Set default timeouts - I saw it wait 5 mins before timing out a failure to load the Home page

10. create a proper README.md to start building on. Include:
    1. java version
    2. how to run locally - with mvn and directly from intelliJ
   
11. session variables - do we even want them?

12. buildspec.yml will be need for pipeline config
    1. deletion sql script to be included as a post-build step
    2. reporting - where will it be saved - S3 bucket probably
    
13. explore how the "screenplay" format can be included here

