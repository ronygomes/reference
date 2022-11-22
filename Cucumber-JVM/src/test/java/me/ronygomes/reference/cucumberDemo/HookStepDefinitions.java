package me.ronygomes.reference.cucumberDemo;

import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/*
    Hooks will run after each Scenario/Example. Added tag @hook_enabled to make sure
    these runs with only hooks.feature, otherwise test fails for count mismatch
 */
public class HookStepDefinitions {

    private static final Map<String, List<Integer>> ORDER_MAP = new HashMap<>();

    private static int counter = 0;

    static {
        ORDER_MAP.put("BeforeAll", singletonList(1));
        ORDER_MAP.put("AfterAll", singletonList(13));

        ORDER_MAP.put("Before", singletonList(2));
        ORDER_MAP.put("After", singletonList(12));

        ORDER_MAP.put("BeforeStep", asList(3, 6, 9));
        ORDER_MAP.put("AfterStep", asList(5, 8, 11));

        ORDER_MAP.put("Given", singletonList(4));
        ORDER_MAP.put("When", singletonList(7));
        ORDER_MAP.put("Then", singletonList(10));
    }

    @BeforeAll
    public static void beforeAll() {
        counter++;
        Assertions.assertTrue(ORDER_MAP.get("BeforeAll").contains(counter));
    }

    @AfterAll
    public static void afterAll() {
        counter++;
        Assertions.assertTrue(ORDER_MAP.get("AfterAll").contains(counter));
    }

    @Before("@hook_enabled")
    public void before() {
        counter++;
        Assertions.assertTrue(ORDER_MAP.get("Before").contains(counter));
    }

    @After("@hook_enabled")
    public void after() {
        counter++;
        Assertions.assertTrue(ORDER_MAP.get("After").contains(counter));
    }

    @BeforeStep("@hook_enabled")
    public void beforeStep() {
        counter++;
        Assertions.assertTrue(ORDER_MAP.get("BeforeStep").contains(counter));
    }

    @AfterStep("@hook_enabled")
    public void afterStep() {
        counter++;
        Assertions.assertTrue(ORDER_MAP.get("AfterStep").contains(counter));
    }

    @Given("some random life circumstances")
    public void someRandomLifeCircumstances() {
        counter++;
        Assertions.assertTrue(ORDER_MAP.get("Given").contains(counter));
    }

    @When("you do nothing")
    public void youDoNothing() {
        counter++;
        Assertions.assertTrue(ORDER_MAP.get("When").contains(counter));
    }

    @Then("it will remain same")
    public void itWillRemainSame() {
        counter++;
        Assertions.assertTrue(ORDER_MAP.get("Then").contains(counter));
    }
}
