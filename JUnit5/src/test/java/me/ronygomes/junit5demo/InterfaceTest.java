package me.ronygomes.junit5demo;

/*
 * Executing this file will run 2 test files from exception
 */
public class InterfaceTest implements InterfaceExample {

    @Override
    public boolean isTrue() {
        return true;
    }
}
