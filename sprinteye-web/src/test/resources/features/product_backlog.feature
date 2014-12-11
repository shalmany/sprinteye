# The @txn tag enables a Transaction open-rollback around each Scenario,
# Preventing persisted data from leaking between Scenarios.
@txn
Feature: Product Backlog

  Background: 
    Given that User is registered
    And that Project was created

  Scenario Outline: Add item to Product Backlog
    Given that the user has filled the fields  of product backlog item form with data below
      | name   | itemTypeEnum   | description   | businessValue   | estimate   |
      | <name> | <itemTypeEnum> | <description> | <businessValue> | <estimate> |
    When add item to product backlog
    Then should register the new  item to product backlog

    Examples: 
      | name          | itemTypeEnum | description   | businessValue | estimate |
      | requirement 1 | FEATURE      | description 1 | 400           | 30       |
      | requirement 2 | FEATURE      | description 2 | 100           | 20       |

  Scenario Outline: Add item to Product Backlog without name
    Given that the user has filled the fields  of product backlog item form with data below
         | itemTypeEnum   | description   | businessValue   | estimate   |
        | <itemTypeEnum> | <description> | <businessValue> | <estimate> |
    When add item to product backlog
    Then should show validation Error for Name field of product backlog item

    Examples: 
      | name | itemTypeEnum | description   | businessValue | estimate |
      |      | FEATURE      | description 1 | 400           | 30       |

  Scenario Outline: Add item to Product Backlog without type
    Given that the user has filled the fields  of product backlog item form with data below
      | name     | description   | businessValue   | estimate   |
      | <name>  | <description> | <businessValue> | <estimate> |
    When add item to product backlog
    Then should show validation Error for type field of product backlog item

    Examples: 
      | name          | itemTypeEnum | description   | businessValue | estimate |
      | requirement 1 |              | description 1 | 400           | 30       |
