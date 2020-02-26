Feature: TestCalculatorUI
  Test the UI for a simple calculator

  @UI
  Scenario Outline: Verify Calculator UI using acceptable data type input
    Given I already opened website "https://calculator-web.azurewebsites.net/"
    And I have switched to the iFrame "result"
    When I type in "<LeftNumber>" as the "left number"
    And I choose "<Operator>" as the "operator"
    And I type in "<RightNumber>" as the "right number"
    And I click the calculator button
    Then I should see "<Result>" displayed as result
    Examples:
      | LeftNumber | RightNumber | Operator | Result |
      | 2147483647 | -2147483648 | +        | -1     |
      | 5          | 2           | -        | 3      |
      | 5          | 2           | *        | 10     |
      | 5          | 2           | /        | 2.5    |

  @UI
  Scenario Outline: Verify if user can type in all different length int type numbers
    Given I already opened website "https://calculator-web.azurewebsites.net/"
    And I have switched to the iFrame "result"
    When I type in "<LeftNumber>" as the "left number"
    And I type in "<RightNumber>" as the "right number"
    Then I should see "<LeftNumber>" as the "left number"
    And I should see "<RightNumber>" as the "right number"
    Examples:
      | LeftNumber | RightNumber |
      | 2147483647 | -2147483648 |
      | 214748364  | -214748364  |
      | 21474836   | -21474836   |
      | 2147483    | -2147483    |
      | 214748     | -214748     |
      | 21474      | -21474      |
      | 2147       | -2147       |
      | 214        | -214        |
      | 21         | -21         |
      | 2          | -2          |
      | 0          | 0           |

  @UI
  Scenario: Verify if result text box is readonly
    Given I already opened website "https://calculator-web.azurewebsites.net/"
    And I have switched to the iFrame "result"
    Then I should see the attribute "readonly" of "resultTextBox" is "true"

  #invalid partition start
  @UI
  Scenario: Verify calculator UI divide by zero
    Given I already opened website "https://calculator-web.azurewebsites.net/"
    And I have switched to the iFrame "result"
    When I type in "9" as the "left number"
    And I choose "/" as the "operator"
    And I type in "0" as the "right number"
    And I click the calculator button
    Then I should see "divisor can not be zero" as error message

  @UI
  Scenario: Verify calculator UI with unacceptable data type input
    Given I already opened website "https://calculator-web.azurewebsites.net/"
    And I have switched to the iFrame "result"
    When I type in "abc" as the "left number"
    And I choose "*" as the "operator"
    And I type in "$%6" as the "right number"
    And I click the calculator button
    Then I should see "unacceptable input, please check and try again" as error message
  #invalid partition end
