Feature: Ebay web functionality regression test
  Scenario: Search test (button)
    Given load the Ebay home page
    And enter the "MacBook Pro" into the search field
    When press on the 'search' button
    Then verify that search results are not empty

  Scenario: Search test (enter)
    Given load the Ebay home page
    And enter the "MacBook Air" into the search field
    When press ENTER
    Then verify that search results are not empty

  Scenario: Header verification
    Given load the Ebay home page
    When verify header navigation
    Then verify the 'myEbay' drop down button
    And verify logo has a link to the home page: "https://www.ebay.com/"

  Scenario: 'Shop by category' verification
    Given load the Ebay home page
    When click on the 'Shop by category' button
    Then verify the elements from category list

  Scenario: 'All categories' drop down verification
    Given load the Ebay home page
    And click on the 'All categories' drop down button
    Then verify the list of categories is not empty

  Scenario: Main menu verification
    Given load the Ebay home page
    Then hover over every element and verify the categories are not empty

  Scenario: 'Daily Deals' section verification
    Given load the Ebay home page
    Given scroll to 'Daily Deals' section
    Then get the list of products and verify it's not empty
    And click on the 'See all' button
    Then verify the header "Deals" is visible

  Scenario: Footer verification
    Given load the Ebay home page
    Given scroll to bottom of the page
    Then verify the content of the footer
    And verify the list of sites

  Scenario: Filter functionality test
    Given load the Ebay home page
    Given enter the "iPad Pro" into the search field
    And press ENTER
    Then scroll down and verify the functionality of Filter

  Scenario: Google Ads Verification
    Given load the Ebay home page
    Given enter the "iPad Pro" into the search field
    Given press ENTER
    Then scroll to bottom and verify that the ads section is not empty

  Scenario: Login functionality test
    Given load the Ebay home page
    Given press on the 'Sign in' link
    When get the "username" and "password" from the database
    Then enter the "username" into a username field and press 'Continue' button
    And enter the "password" into a password field press on the 'Sign in' button
    Then verify the successful login in the header section