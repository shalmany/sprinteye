# The @txn tag enables a Transaction open-rollback around each Scenario,
# Preventing persisted data from leaking between Scenarios.
@txn
Feature: Sprint Backlog

  Background: 
    Given that User is registered
    And that Project was created
    And that Product Backlog was created
      | name          | itemTypeEnum | description   | businessValue | estimate |
      | requirement 1 | FEATURE      | description 1 | 400           | 30       |
      | requirement 2 | FEATURE      | description 2 | 100           | 20       |

  Scenario Outline: Create  Sprint Backlog
    Given that the user has filled the fields of sprint backlog form
      | name   | description   |
      | <name> | <description> |
    When submit sprint backlog form
    Then should register the  sprint backlog

    Examples: 
      | name     | description   |
      | sprint 1 | description 1 |

  Scenario Outline: Try create  Sprint Backlog without name
    Given that the user has filled the fields of sprint backlog form
      | description   |
      | <description> |
    When submit sprint backlog form
    Then should show validation Error for Name field of sprint backlog

    Examples: 
      | name | description   |
      |      | description 1 |

  Scenario Outline: Add item to Sprint Backlog
    Given that Sprint Backlog was created
      | name     | description   |
      | sprint 1 | description 1 |
    And that selected some items from product backlog
    When add items to sprint backlog
    Then should register the items  in sprint backlog

    Examples: 
      | name     | description   |
      | sprint 1 | description 1 |
