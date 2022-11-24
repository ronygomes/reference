package me.ronygomes.reference.cucumberDemo;

import io.cucumber.guice.ScenarioScoped;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

@ScenarioScoped
public class RegExExpressionStepDefinitions {

    private int number;


    @Given("^some number (\\d+)$")
    public void someNumber(int num) {
        this.number = num;
    }


    @When("^trying to parse using (.*)$")
    public void tryingToParseUsingRegEx(String name) {
        Assertions.assertEquals("RegEx", name);
    }

    @Then("^it will get (\\d+)$")
    public void itWillGet(int expected) {
        Assertions.assertEquals(expected, number);
    }

    @Then("it will pass")
    public void itWillPass() {
    }
}
