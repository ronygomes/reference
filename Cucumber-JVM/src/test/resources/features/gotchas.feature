Feature: Example of some inconsistencies

  Example:

  If same Text as given as Given/When/Then then single step is enough.
  Note in java code only @Given("same text") is defined but it is called for all step functions
  This is intended as it forces us to write better step text

    Given same text
    When same text
    Then same text

  Example:

  For configuring dependency inject need to configure following steps
    - Write a implementation of ObjectFactory
    - Add this implementation with @ConfigurationParameter
    - Expose this class with Java Service Loader API
    - Gotchas - If any property is declared in step definition class need to add @ScenarioScoped.
      Didn't find anything to stop it

    Given GreetingService injected by Cucumber
    When calling the greet() method
    Then should get "Hello World!"
