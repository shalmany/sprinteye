# The @txn tag enables a Transaction open-rollback around each Scenario,
# Preventing persisted data from leaking between Scenarios.
@txn
Feature: Project

  Background: 
    Given that User is registered

  Scenario Outline: Create Project with all fields
    Given the User filled the  name of project with <name>
    And the User filled the  description of project with <description>
    When click button register of project form
    Then should register the new project

    Examples: 
      | name      | description   |
      | Project 1 | description 1 |
      | Project 2 | description 2 |

  Scenario Outline: Try create Project without the name
   
    Given the User filled the  description of project with <description>
    When click button register of project form
    Then should show validation Error for Name field of project

    Examples: 
      | name | description   |
      |      | description 1 |
