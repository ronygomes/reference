package me.ronygomes.reference.cucumberDemo;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CucumberExpressionStepDefinitions {

    @Given("some number integer number: byte {byte}, short {short}, int {int}, long {long}")
    public void someIntegerNumber(byte b, short s, int i, long l) {
        Assertions.assertEquals(1, b);
        Assertions.assertEquals(2, s);
        Assertions.assertEquals(3, i);
        Assertions.assertEquals(4, l);
    }

    @When("trying to parse with Cucumber Expression")
    public void tryingToParseWithCucumberExpression() {
    }

    @Given("some number real number: float {float}, double {double}")
    public void someNumberRealNumberFloatDouble(float f, double d) {
        Assertions.assertEquals(3.5, f);
        Assertions.assertEquals(10.3, d);
    }

    @Given("some string: Word {word} and Sentence {string}")
    public void someStringWordDummyAndSentence(String word, String sentence) {
        Assertions.assertEquals("Dummy", word);
        Assertions.assertEquals("Hello World", sentence);
    }

    @Given("some big numbers as: BigInteger {biginteger}, BigDecimal {bigdecimal}")
    public void someBigNumbersAsBigIntegerBigDecimal(BigInteger i, BigDecimal d) {
        Assertions.assertEquals(3, i.intValue());
        Assertions.assertEquals(10.3, d.doubleValue());
    }

    @Given("any matcher: {} but not this")
    public void anyMatcherThisWillBeCatchedButNotThis(String text) {
        Assertions.assertEquals("This will be Catched", text);
    }

    @Given("I have {int} cucumber(s) in my belly/stomach")
    public void iHaveCucumberInMyBelly(int ignore) {
    }
}
