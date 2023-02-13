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

## Creating new tests
#### 1. feature file / step definition / page object - use existing code where appropriate before creating new ones.
####

#### 2. creating a new PageObject
   ####  
   - extend [BasePageOject](src/test/java/pages/BasePageObject.java) 
     #### 
   - implement the appropriate [Interface(s)](src/test/java/navigationPanel) based on which links are accessible from the given page
     #### 
   - create the expected page title var
     - `private static final String expectedPageTitle = "National Delius - Login";
       `
       ####
   - create a public page constructor such as, including the page title assertion:
     - `public LoginPage(WebDriver webDriver) {
       super(webDriver);
       assertPageTitle(expectedPageTitle);
       }`
       ####

   - all webElements should be private
     - `@FindBy(id = "j_username")
       private WebElement usernameField;`
       ####

   - all public 'action' methods within the PageObject class should:

      1. return the same PageObject when that action does not trigger a new page object
        - `public LoginPage enterLoginDetails() {
          usernameField.sendKeys("username");
          passwordField.sendKeys("password");
          return this;
          }`
          ####

      2. return the appropriate page object when an action triggers it
         - `public HomePage clickLoginButton() {
           loginButton.click();
           return new HomePage(getWebDriver());
           }`
           ####

#### 3. writing Scenarios
####  
- ensure steps targets the purpose of the scenario


           
