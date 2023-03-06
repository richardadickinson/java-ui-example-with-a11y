# java-ui-automation

The test suite herein contains automated end-to-end tests for the any application and its satellite resources,
including accessing a test data API. This automated test pack uses [Cucumber](https://cucumber.io/docs) and testNG.

## Java version

Use Java 18 or above.

## Running the Tests

### Locally

To run the tests against an environment from your local machine:

1. set the environment and database password as environment variables:
 - DB_PASSWORD 
 - ENVIRONMENT is an optional variable with the default as 'test' environment. To run locally against other environments such as stage or pre-prod, variable must be set as per below.
```shell
export DB_PASSWORD="value"
export ENVIRONMENT="stage", "pre-prod"
```
3. Open a tunnel to the database:

```shell
ssh ssh -L localhost:<db-path>:1521 <db-path> -Nv -Snone
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

### In AWS CodeBuild

To run the tests in AWS CodeBuild,

1. Assume the correct role in `test`
2. Navigate to the CodeBuild project.
3. Click **Start build**, or **Start build with overrides** to specify a different branch or environment variables - in
   particular `CUCUMBER_FILTER_TAGS`.


## Creating new tests

#### 1. feature file / step definition / page object - use existing code where appropriate before creating new ones.

####

#### 2. creating a new PageObject

####    

- extend [BasePageOject](src/test/java/pages/BasePageObject.java)
  ####   
- implement the appropriate [Interface(s)](src/test/java/navigationPanel) based on which links are accessible from the
  given page
  ####   
- create the expected page title var
    - `private static final String expectedPageTitle = "Login";
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


           
