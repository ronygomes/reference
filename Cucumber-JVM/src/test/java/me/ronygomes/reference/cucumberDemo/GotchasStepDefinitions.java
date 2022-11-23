package me.ronygomes.reference.cucumberDemo;

import io.cucumber.java.en.Given;
import org.junit.jupiter.api.Assertions;

public class GotchasStepDefinitions {

    private int previousCount = 0;
    private int count = 0;

    @Given("same text")
    public void sameText() {
        count++;
        Assertions.assertEquals(previousCount, count - 1);
        previousCount = count;
    }
}
