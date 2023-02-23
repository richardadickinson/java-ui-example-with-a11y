Feature: Search results POC

  Scenario: Select from Search results table by CRN
    Given I search for and select an offender
    Then The Personal Details page matches the offender selected

  Scenario: Can access session data as necessary when multiple persons are created
    Given I search for and select second person
    Then The Personal Details page matches the person selected

  Scenario: Can create and access multiple persons are created at one
    Given I create 4 persons
    And I search for and select second person
    Then The Personal Details page matches the person selected