# The @txn tag enables a Transaction open-rollback around each Scenario,
# Preventing persisted data from leaking between Scenarios.

@txn
Feature: Product Backlog

   Background:
    Given that User is registered
    And that Project was created
    
   Scenario: Add item to Product Backlog
    Given the User complete all fields of product backlog item form
    When add item to product backlog  
    Then should register the new  item to product backlog 
