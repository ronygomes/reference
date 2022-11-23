Feature: Special input type

  Example:

  It is possible to input large text in following way. These are called doc string.
  Step methods will get the data as last arguments. It can be """ or ```

    Given a Doc String "Triple Double Quote"
    """
    Hello World!
    Line 2
    """
    When string length is calculated
    Then Length will be 19


  Example:

    Given a Doc String "Triple Backtick"
    ```
    Hello World!
    Line 2
    ```
    When string length is calculated
    Then Length will be 19

  Example:

    Given a Doc String "Triple Backtick and type"
    ```text
    Hello World!
    Line 2
    ```
    When string length is calculated
    Then Length will be 19

  Example:

    Cucumber-JVM will inject following table as List<String> in Java step function

    Given following Fruits
    | Orange |
    | Apple  |
    | Pears  |
    When sorted alphabetically
    Then first fruit will be "Apple"

  Example:

  Cucumber-JVM will inject following table as Map<String, Integer> in Java step function

    Given following Fruits with sorting order
      | Orange |  2 |
      | Apple  |  3 |
      | Pears  |  1 |
    When sorted alphabetically using order
    Then first fruit will be "Pears"

  Example:

    It supports following structures based on input type
    List<List<String>> table
    List<Map<String, String>> table
    Map<String, String> table
    Map<String, List<String>> table
    Map<String, Map<String, String>> table

    Given following random data
    |        |    color  | price |
    | apple  |     red   |  50   |
    | orange |   orange  |  70   |
    When doing nothing
    Then verify table structure

  Example:

    Can escape control character using following way
    Newline - \n
    Backslash - \\
    Pipe - \|

    Given following table with escaped data
      | newline   |  Line 1\nLine 2   |
      |   pipe    |  history \| grep   |
      | backslash |   one \\ two       |
    When doing nothing
    Then doing nothing
