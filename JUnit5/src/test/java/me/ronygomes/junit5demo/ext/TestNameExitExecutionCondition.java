package me.ronygomes.junit5demo.ext;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TestNameExitExecutionCondition implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

        ConditionEvaluationResult result = ConditionEvaluationResult.enabled("Everything is Ok");
        String testName = context.getTestMethod().orElseThrow(IllegalAccessError::new).getName();

        if (testName.equals("testExit")) {
            result = ConditionEvaluationResult.disabled("testExit() method called");
        }

        return result;
    }
}
