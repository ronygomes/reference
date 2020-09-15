package me.ronygomes.greeter.spi;

public interface GreeterProvider {
    Greeter create();
}
