package me.ronygomes.reference.cucumberDemo;

public class Calculator {

    public static final char CALCULATOR_ADD = '+';
    public static final char CALCULATOR_SUBTRACT = '-';

    private int num1;
    private int num2;
    private char action;

    public int display() {
        switch (action) {
            case CALCULATOR_ADD:
                return num1 + num2;
            case CALCULATOR_SUBTRACT:
                return num1 - num2;
            default:
                throw new IllegalArgumentException("Invalid action: " + action);
        }
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public char getAction() {
        return action;
    }

    public void setAction(char action) {
        this.action = action;
    }
}
