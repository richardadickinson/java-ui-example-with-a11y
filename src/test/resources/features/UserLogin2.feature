Feature: login 2

  Scenario: Standard user login 2
    Given I have a valid user credentials
    When I login
    Then the Homepage should appear