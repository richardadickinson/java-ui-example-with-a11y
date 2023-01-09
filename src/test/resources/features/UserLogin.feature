Feature: login

  Scenario: Standard user login
    Given I have a valid user credentials
    When I login
    Then the Homepage should appear