# language: en
# It is recommended to write features in native language so that meaning doesn't change while translation
# Cucumber has 40+ language supports and the first line of feature file tells Cucumber what spoken language to use.
# Default is english

# Line starting with `#' as first non whitespace character will considered as comment
# Recommendation is to use 2 space for indentation in Gherkin file
@ui
Feature: Summary of the Gherkins keywords

  This paragraph will be considered as the detailed description of the feature
  It will end until next keyword (i.e. Scenario, Background) is found
  It is possible to add description in following section Example/Scenario, Background, Scenario Outline and Rule.
  Description line can to start with Keyword, as those has specific meaning.
  Tag are prefixed with `@`. There are marker like JUnit which can filter Scenarios

  Commenting before this section will throw exception
  # But Can have comment after (here)

  Background:

    Common prerequisites are given in 'Background' section. These are 'Given' statement will run for all Scenario in this feature
    Only 1 Background per feature but Rule can also have Background

    Given User is successfully logged in to the system

  @web
  Scenario: Provide a concrete example or Scenario

    These are the heart of Gherkin. Each Feature will have multiple 'Scenario' in following Structure
    Specify the initial state with 'Given'
    Perform the action in 'When' step
    These 3 combined steps are analogous to junit @Test method

    Given a Calculator
    When 2 and 3 are given for adding
    Then display will show 5

  @slow
  Example:

    Keyword `Example' is alias to Scenario. Here will play with
    With `AND', `BUT' and `*' it is possible to continues the previous Given/When/Then

    Given a Counter
    And being Curious
    When Count button in pressed
    And Count button in pressed
    But Count button in pressed
    * Count button in pressed
    Then Total Count will be 4
    But I am bored

  @desktop @slow
  Scenario Outline: Add two numbers

    With Scenario Outline it is possible to run multiple Example with similar input
    It will execute both Example or Scenario.

    Examples:

      Following will run 3 times with provided inputs. <num1>, <num2>, <result> will replaces
      Note: No new Java code is written for it, will run on previously written steps

      Given a Calculator
      When <num1> and <num2> are given for adding
      Then display will show <result>

      | num1 | num2 | result |
      |   2  |   3  |   5    |
      |  12  |   3  |   15   |
      |  10  |   5  |   15   |

  Scenario Template: Add some more two numbers

    It is alias to Scenario Outline.

    Scenarios:
    Keyword Scenarios is alias to `Examples` and also works with Scenario Outline

    Given a Calculator
    When <num1> and <num2> are given for adding
    Then display will show <result>

      | num1 | num2 | result |
      |  12  |   3  |   15   |
      |   1  |   3  |    4   |
      |  19  |   5  |   24   |

  Rule: Group Scenario with Rule

    Gherkin 6 introduced a new Keyword `Rule'. It is possible to group Scenario or Example with
    it. It can take a Background which is specific to this Rule

    Background:

      Following are prerequisite to the rule
      Given User is an admin

    Example:

      Given User is on Dashboard page
      When Save button is clicked
      Then shows "LoggedInAdminDashboard"
