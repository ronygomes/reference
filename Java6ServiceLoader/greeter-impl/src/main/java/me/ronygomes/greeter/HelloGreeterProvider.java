package me.ronygomes.greeter;

import me.ronygomes.greeter.spi.Greeter;
import me.ronygomes.greeter.spi.GreeterProvider;

public class HelloGreeterProvider implements GreeterProvider {

    @Override
    public Greeter create() {
        return new HelloGreeter();
    }
}
