package me.ronygomes.reference.cucumberDemo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class AllKeywordStepDefinitions {

    private Calculator calculator;
    private Counter counter;

    private String actionMessage;

    @Given("User is successfully logged in to the system")
    public void userIsSuccessfullyLoggedInToTheSystem() {
        actionMessage = "LoggedIn";
    }

    @Given("being Curious")
    public void beingCurious() {
        System.out.println("I am Curious!");
    }

    @Given("a Calculator")
    public void createCalculator() {
        this.calculator = new Calculator();
    }

    @Given("a Counter")
    public void createCounter() {
        this.counter = new Counter();
    }

    @When("{int} and {int} are given for adding")
    public void addNumber(int a, int b) {
        calculator.setNum1(a);
        calculator.setNum2(b);
        calculator.setAction(Calculator.CALCULATOR_ADD);
        System.out.println("a:b -> " + a + ":" + b);
    }

    @When("Count button in pressed")
    public void countButtonPressed() {
        counter.countOne();
    }

    @Then("display will show {int}")
    public void assertDisplay(int number) {
        Assertions.assertEquals(number, calculator.display());
    }

    @Then("Total Count will be {int}")
    public void assertTotalCount(int number) {
        Assertions.assertEquals(number, counter.totalCount());
    }

    @Then("I am bored")
    public void assertTrue() {
        Assertions.assertTrue(true);
    }

    @Given("User is on Dashboard page")
    public void userIsOnDashboardPage() {
        actionMessage += "Dashboard";
    }

    @Given("User is an admin")
    public void userIsAnAdmin() {
        actionMessage += "Admin";
    }

    @When("Save button is clicked")
    public void saveButtonIsClicked() {
        System.out.println("Do Nothing");
    }

    @Then("shows {string}")
    public void showsLoggedInAdminDashboard(String message) {
        Assertions.assertEquals(message, actionMessage);
    }
}
