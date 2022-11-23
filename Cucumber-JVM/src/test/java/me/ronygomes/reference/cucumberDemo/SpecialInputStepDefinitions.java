package me.ronygomes.reference.cucumberDemo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SpecialInputStepDefinitions {

    private String message;
    private int length;
    private List<String> data;
    private Map<String, Integer> map;
    private Map<String, Map<String, String>> complexData;

    @Given("a Doc String {string}")
    public void docString(String name, String docString) {
        System.out.println("First argument is for {string}: " + name);
        this.message = docString;
    }

    @When("string length is calculated")
    public void calculateString() {
        length = message.length();
    }

    @Then("Length will be {int}")
    public void lengthWillBe(int length) {
        Assertions.assertEquals(length, this.length);
    }

    @Given("following Fruits")
    public void followingFruits(List<String> fruits) {
        this.data = new ArrayList<>(fruits);
    }

    @When("sorted alphabetically")
    public void sortedAlphabetically() {
        Collections.sort(this.data);
    }

    @Then("first fruit will be {string}")
    public void firstFruitWillBe(String head) {
        Assertions.assertEquals(head, data.get(0));
    }

    @Given("following Fruits with sorting order")
    public void followingFruitsWithSortingOrder(Map<String, Integer> fruits) {
        this.map = fruits;
    }

    @When("sorted alphabetically using order")
    public void sortedAlphabeticallyUsingOrder() {
        List<String> sortedFruits = new ArrayList<>(map.keySet());
        for (Map.Entry<String, Integer> fruit : map.entrySet()) {
            sortedFruits.set(fruit.getValue() - 1, fruit.getKey());
        }
        this.data = sortedFruits;
    }

    @When("doing nothing")
    public void doingNothing() {
    }

    @Given("following random data")
    public void followingRandomData(Map<String, Map<String, String>> table) {
        this.complexData = table;
    }

    @Then("verify table structure")
    public void verifyTableStructure() {
        Assertions.assertEquals("red", complexData.get("apple").get("color"));
        Assertions.assertEquals("orange", complexData.get("orange").get("color"));
        Assertions.assertEquals("50", complexData.get("apple").get("price"));
        Assertions.assertEquals("70", complexData.get("orange").get("price"));
    }

    @Given("following table with escaped data")
    public void followingTableWithEscapedData(Map<String, String> data) {
        System.out.println(data.get("newline"));
        System.out.println(data.get("pipe"));
        System.out.println(data.get("backslash"));
    }
}
