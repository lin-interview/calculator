Feature: TestCalculatorAPI
  Test the rest API of a simple calculator

  #valid partition
  @API
  Scenario Outline: Verify Calculator API using acceptable data type
    Given I have one calculator API
    When I perform POST operation for "Calculate" with <LeftNumber> <RightNumber> and "<Operator>"
    Then the response code should be "200"
    And I should see the body has "Value" as "<Result>"
    Examples:
      | LeftNumber | RightNumber | Operator | Result |
      | 2147483647 | -2147483648 | +        | -1     |
      | 5          | 2           | -        | 3      |
      | 5          | 2           | *        | 10     |
      | 5          | 2           | /        | 2.5    |

  #invalid partition start
  @API
  Scenario Outline: Verify calculator API with unacceptable data type
    Given I have one calculator API
    When I perform POST operation for "Calculate" with unacceptable type "<LeftNumber>" "<RightNumber>" and "<Operator>"
    Then the response code should be "400"
    And I should see the body has "Error Msg" as "unacceptable data type"
    Examples:
      | LeftNumber  | RightNumber | Operator |
      | 2147483647  | -2147483648 | +        |
      | 1.234       | 2           | -        |
      | -2147483648 | 0           | /        |
      | 5           | 2           | %        |

  @API
  Scenario: Verify calculator API divide by zero
    Given I have one calculator API
    When I perform POST operation for "Calculate" with 1 0 and "/"
    Then the response code should be "400"
    And I should see the body has "Error Msg" as "divisor can not be zero"
  #invalid partition end
