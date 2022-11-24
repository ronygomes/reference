Feature: Cucumber can table custom parameter

  Example:

    Given some person info as Person(John, 25)
    When doing nothing
    Then it will pass

    Given person info as table
    | name | age |
    | John | 25  |
    When doing nothing
    Then it will pass

    Given person info as xml
    """
    <root>
      <name>John</name>
      <age>25</age>
    </root>
    """
    When doing nothing
    Then it will pass