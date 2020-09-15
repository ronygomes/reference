package me.ronygomes.greeter;

import me.ronygomes.greeter.spi.Greeter;

public class HelloGreeter implements Greeter {

    @Override
    public String greet() {
        return "Hello World!";
    }
}
