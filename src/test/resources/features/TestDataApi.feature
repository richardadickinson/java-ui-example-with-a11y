@api
Feature: Tests for using Test Data API

  Scenario: Create an offender
    Given Create offender endpoint is called
    Then offender is created with new CRN
    When offender is updated
    Then updates are validated