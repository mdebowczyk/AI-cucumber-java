Feature: Google Search

  Scenario: User performs a search on Google
    Given I am on the Google search page
    When I enter "Selenium" in the search bar
    And I click the search button
    Then the search results for "Selenium" are displayed
