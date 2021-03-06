@API
Feature: Test Calculator API
  Test the rest API of a simple calculator
  Background:
    Given I have one calculator API

  #valid partition
  Scenario Outline: Verify Calculator API using acceptable data type
    When I perform POST operation for "Calculate" with <LeftNumber> "<Operator>" <RightNumber>
    Then the response code should be "200"
    And I should see the body has "Value" as "<Result>"
    Examples:
      | LeftNumber | RightNumber | Operator | Result |
      | 2147483647 | -2147483648 | +        | -1     |
      | 5          | 2           | -        | 3      |
      | 5          | 2           | *        | 10     |
      | 5          | 2           | /        | 2.5    |

  #invalid partition start
  Scenario Outline: Verify calculator API with unacceptable data type
    When I perform POST operation for "Calculate" with unacceptable type "<LeftNumber>" "<Operator>" "<RightNumber>"
    Then the response code should be "400"
    And I should see the body has "Error Msg" as "unacceptable data type"
    Examples:
      | LeftNumber  | RightNumber | Operator |
      | 2147483647  | -2147483648 | +        |
      | 1.234       | 2           | -        |
      | -2147483648 | 0           | /        |
      | 5           | 2           | %        |

  Scenario: Verify calculator API divide by zero
    When I perform POST operation for "Calculate" with 1 "/" 0
    Then the response code should be "400"
    And I should see the body has "Error Msg" as "divisor can not be zero"
  #invalid partition end
