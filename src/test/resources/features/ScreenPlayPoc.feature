Feature: Validate Screenplay POC

  Scenario: Validate Event details
    Given an offender with an event and contact is created
    When I login
    And I navigate to search the CRN
    Then the CRN should be returned in the search results