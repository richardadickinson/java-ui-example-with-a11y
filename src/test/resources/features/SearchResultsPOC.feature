Feature: Search results POC

  Scenario: Select from Search results table by CRN
    Given I search for and select an offender
    Then The Personal Details page matches the offender selected