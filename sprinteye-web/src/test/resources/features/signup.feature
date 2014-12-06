# The @txn tag enables a Transaction open-rollback around each Scenario,
# Preventing persisted data from leaking between Scenarios.

@txn
Feature: Signup

  Scenario: Show Sign up page
    Given the User has requested a signup page "/todo/signup"
    Then should show signup page
    
  Scenario: Sign up without password
    Given a User filled form without password
    When click button register of sign up form
    Then should show validation Error for Password field
    
   Scenario: Sign up with all fields
    Given the User complete all fields of sign up form
    When click button register of sign up form
    Then should register the new user
