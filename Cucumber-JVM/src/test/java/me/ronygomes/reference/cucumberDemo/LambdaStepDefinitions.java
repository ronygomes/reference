package me.ronygomes.reference.cucumberDemo;

import io.cucumber.java8.En;

// Requires dependency io.cucumber:cucumber-java8
public class LambdaStepDefinitions implements En {

    public LambdaStepDefinitions() {
        Given("some condition as Given lambda", () -> {

        });

        When("When lambda is called", () -> {

        });

        Then("it will same as Then lambda", () -> {

        });
    }
}
