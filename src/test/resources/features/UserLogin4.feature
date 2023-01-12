Feature: login 4

  Scenario: Standard user login 4
    Given I have a valid user credentials
    When I login
    Then the Homepage should appear