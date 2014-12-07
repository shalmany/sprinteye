# The @txn tag enables a Transaction open-rollback around each Scenario,
# Preventing persisted data from leaking between Scenarios.
@txn
Feature: Product Backlog

  Background: 
    Given that User is registered
    And that Project was created

  Scenario Outline: Add item to Product Backlog
    Given that fill <name> in name field  of product backlog item form
    And that fill <type> in type field  of product backlog item form
    And that fill <description> in description field  of product backlog item form
    And that fill <businessValue> in business value field  of product backlog item form
    And that fill <estimate> in estimate field  of product backlog item form
    When add item to product backlog
    Then should register the new  item to product backlog

    Examples: 
      | name          | type    | description   | businessValue | estimate |
      | requirement 1 | FEATURE | description 1 | 400           | 30       |
      | requirement 2 | FEATURE | description 2 | 100           | 20       |

  Scenario Outline: Add item to Product Backlog without name
    Given that fill <type> in type field  of product backlog item form
    And that fill <description> in description field  of product backlog item form
    And that fill <businessValue> in business value field  of product backlog item form
    And that fill <estimate> in estimate field  of product backlog item form
    When add item to product backlog
    Then should show validation Error for Name field of product backlog item

    Examples: 
      | name | type    | description   | businessValue | estimate |
      |      | FEATURE | description 1 | 400           | 30       |

  Scenario Outline: Add item to Product Backlog without type
    Given that fill <name> in name field  of product backlog item form
    And that fill <description> in description field  of product backlog item form
    And that fill <businessValue> in business value field  of product backlog item form
    And that fill <estimate> in estimate field  of product backlog item form
    When add item to product backlog
    Then should show validation Error for type field of product backlog item

    Examples: 
      | name          | type | description   | businessValue | estimate |
      | requirement 1 |      | description 1 | 400           | 30       |
