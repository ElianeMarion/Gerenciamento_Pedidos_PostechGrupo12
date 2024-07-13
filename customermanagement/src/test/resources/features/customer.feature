Feature: Customer
  Scenario: Register Customer
    When register new customer
    Then customer registered successfully
    And customer is shown

  Scenario: Find Customer
    Given customer was registered
    When find customer
    Then customer found
    And customer is shown

  Scenario: Find Customer by name
    Given customer was registered
    When find customer by name
    Then customer found
    And list of customer is shown

  Scenario: Update Customer
    Given customer was registered
    When update customer
    Then customer updated successfully
    And customer is shown

  Scenario: Delete Customer
    Given customer was registered
    When delete customer
    Then NoContent
