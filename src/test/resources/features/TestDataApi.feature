@api
Feature: Tests for using Test Data API

  Scenario: Create, update and Get an offender
    Given Create offender endpoint is called
    Then offender is created with new CRN
    When offender is updated
    Then offender updates are validated by GET call

  Scenario: Create, update by CRN and Get an Event
    Given an offender with event is created
    Then event is created with new event Id
    When event is updated by CRN
    Then event updates are validated by GET call

  Scenario: Create, update by event ID and Get an Event
    Given an offender with event is created
    Then event is created with new event Id
    When event is updated by Event ID
    Then event updates are validated by GET call

    Scenario: Create, update and GET a Contact
      Given an offender with contact is created
      Then contact is created with a contact Id
      When contact is updated
      Then contact updates are validated by GET call
