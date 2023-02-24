Feature: Create and access multiple objects

Scenario: Create and access session data when multiple persons are created one at a time
Given I create multiple persons one at a time
When I search for and select second person
Then The Personal Details page matches the person selected

Scenario: Create and access session data when multiple persons are created at once
Given I create 4 persons at once
When I search for and select second person
Then The Personal Details page matches the person selected