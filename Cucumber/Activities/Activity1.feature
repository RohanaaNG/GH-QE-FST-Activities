Feature: Open website

  @Activity1
  Scenario: open browser and load URL
    Given I open the browser
    When I navigate to "https://training-support.net"
    Then the page title contains "Example Domain"
