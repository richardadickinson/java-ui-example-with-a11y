# ndelius-ui-automation

The test suite herein contains automated end-to-end tests for the Delius application and its satellite resources,
including Secure Partner Gateway (SPG) and Alfresco document management. This automated test pack uses
[Cucumber](https://cucumber.io/docs) and testNG.

## Java version

Use Java 18 or above.

## Running the Tests

### Locally

To run the tests against the Delius environment from your local machine:

1. set the environment and database password as an environment variable by running:
```shell
export DB_PASSWORD="value"
export ENV="test" [either "test, stage, pre-prod"]
```
3. Open a tunnel to the database:

```shell
ssh ssh -L localhost:1801:delius-db-1.test.delius.probation.hmpps.dsd.io:1521 delius-db-1.test.delius.probation.hmpps.dsd.io -Nv -Snone
```

3. Run the tests:
    - Run the tests in IntelliJ, adding required tags to the program arguments in your runner, if needed:
   ```
    --tags @insert-your-tag-expression-here
   ```
    - Run the tests directly with Maven:
   ```shell
   CUCUMBER_FILTER_TAGS='insert-your-tag-expression-here' mvn clean test
   ```