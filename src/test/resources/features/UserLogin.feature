Feature: login 1

  Scenario: Standard user login 1
    Given I have a valid user credentials
    When I login
    Then the Homepage should appear