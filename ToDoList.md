ToDo list:

1. figure out how to read properties file for given environment  - DONE
   
2. ability to configure and run tests on multiple threads (tests should be written so that they are independent) - DONE

3. ability to automatically assert each page header - screenplay - assertion placed in the PageObject Constructor - DONE

4. Data management
   1. Data creation (Offender, Event, Contacts)
      1. API - RestAssured
         1. e.g. login, create an offenderData, search offenderData, update offenderData - ISSUE - fixed: Event endpoint - DONE
         3. Delete - no API endpoints yet - do we wait or use SQL?? - DONE (used SQL)
         4. Breakdown of JSON quest for each endpoint - e.g. "mainOffenceCode": "00856" - where is the value coming from? book time with devs to understand
         5. API Request JSON management - do we maintain specific JSONs - what about data randomisation?
      2. Reference Data - do we need to create specific ones just for automation pack? if so, how will we do that? sql injections?
      3. user data (usernames, passwords)  -- enums added - DONE
   
5. Database query management  -- DONE
   - connection pool - DONE
   - POC use db query in step assertion - DONE
   
6. Reporting  -- use cucumber?? - add better metrics - check new java Accelerator framework - basic cucumber reporting is turned on - ongoing
7. set up project in Codebuild
8. enable analysis function in CI tool (codebuild in AWS) - to help identify flaky tests etc... 

9. POC - smoke test added to this for higher env to run in Edge - not started

10. Set default timeouts - set to 30 seconds - DONE

11. create a proper README.md to start building on. Include:
    1. java version - DONE
    2. how to run locally - with mvn and directly from intelliJ - DONE
    3. how to write tests - STARTED
   
12. session variables - do we even want them? - DONE (SessionData)

13. buildspec.yml for pipeline config
    1. deletion sql script to be included as a post-build step - NOT REQUIRED (called in test @After step)
    2. reporting - where will it be saved - S3 bucket probably
    
14. explore how the "screenplay" format can be included here - DONE
15. Map user home area in accordance with offender creation data to allow viewing access - STARTED - need to follow up
16. add sign out on After test - DONE

17. if it's ever necessary to run the frameworkTests from maven (e.g. automated in build process) then a maven plugin will be needed for managing the jetty server.
    https://wiki.eclipse.org/Jetty/Feature/Jetty_Maven_Plugin - investigate mvn verify vs test

