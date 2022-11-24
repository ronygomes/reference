package me.ronygomes.reference.cucumberDemo;

import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.bs.I;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;

@ScenarioScoped
public class GotchasStepDefinitions {

    private int previousCount = 0;
    private int count = 0;

    @Inject
    private GreetService greetService;

    @Given("same text")
    public void sameText() {
        count++;
        Assertions.assertEquals(previousCount, count - 1);
        previousCount = count;
    }

    @Given("GreetingService injected by Cucumber")
    public void someObjectToInject() {

    }

    @When("calling the greet\\() method")
    public void callingTheGreetMethod() {

    }

    @Then("should get {string}")
    public void shouldGet(String expected) {
        Assertions.assertEquals(expected, greetService.greet());
    }
}
