@a11y
Feature: Accessibility POC

  Scenario: Analyse the Login and Home pages with Axe
    Given the Login page is loaded
    Then Axe analyses the "Login" page and produces a report
    When the user logs in
    Then Axe analyses the "Home" page and produces a report