Feature: Cucumber supports RegEx Expression

  Cucumber matching string can be written as Regular Expression or Cucumber Expression
  By default it assumes it to be Cucumber Expression. These expression is easier and better for readability

  Example:

    Given some number integer number: byte 1, short 2, int 3, long 4
    When trying to parse with Cucumber Expression
    Then it will pass

  Example:

    Given some number real number: float 3.5, double 10.3
    When trying to parse with Cucumber Expression
    Then it will pass

  Example:

    Given some big numbers as: BigInteger 3, BigDecimal 10.3
    When trying to parse with Cucumber Expression
    Then it will pass

  Example:

    Note {string} will match everything inside quote "", Here Hello World without quote
    but {word} doesn't need "". If given will include like "Dummy" (with ")

    Given some string: Word Dummy and Sentence "Hello World"
    When trying to parse with Cucumber Expression
    Then it will pass

  Example:

    Given any matcher: This will be Catched but not this
    When trying to parse with Cucumber Expression
    Then it will pass


  Example:

    Expression "I have {int} cucumber(s) in my belly/stomach" will match following
    > I have 1 cucumber in my belly
    > I have 2 cucumbers in my belly
    > I have 2 cucumbers in my stomach
    > I have 1 cucumber in my stomach

    Given I have 1 cucumber in my belly
    And I have 2 cucumbers in my belly
    And I have 2 cucumbers in my stomach
    And I have 1 cucumber in my stomach
    When trying to parse with Cucumber Expression
    Then it will pass
