# The @txn tag enables a Transaction open-rollback around each Scenario,
# Preventing persisted data from leaking between Scenarios.

@txn
Feature: Project

   Background:
    Given that User is registered
    
   Scenario: Create Project with all fields
    Given the User complete all fields of project form
    When click button register of project form
    Then should register the new project
