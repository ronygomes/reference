Feature: Example of some inconsistencies

  Example:

  If same Text as given as Given/When/Then then single step is enough.
  Note in java code only @Given("same text") is defined but it is called for all step functions
  This is intended as it forces us to write better step text

    Given same text
    When same text
    Then same text
