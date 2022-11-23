Feature: Cucumber supports RegEx Expression

  Example:

    Cucumber matching string can be written as Regular Expression or Cucumber Expression
    By default it assumes it to be Cucumber Expression. Unless text in this format ^text$
    or contain \ in the string

    Given some number 42
    When trying to parse using RegEx
    Then it will get 42
