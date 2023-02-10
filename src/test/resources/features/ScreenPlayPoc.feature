Feature: Screenplay POC for Offender, Event and Contact Details

  Scenario: Validate Offender details
    Given an offender is created
    When I navigate to Personal Details page
    Then the offender details should be present

  Scenario: Validate Event details
    Given an offender with event is created
    When I navigate to Event Details page
    Then the event details should be present

  Scenario: Validate Contact details
    Given an offender with contact is created
    When I navigate to Contact Details page
    Then the contact details should be present