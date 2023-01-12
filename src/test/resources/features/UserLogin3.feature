Feature: login 3

  Scenario: Standard user login 3
    Given I have a valid user credentials
    When I login
    Then the Homepage should appear