package me.ronygomes.greeter.client;

import me.ronygomes.greeter.spi.Greeter;
import me.ronygomes.greeter.spi.GreeterProvider;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {

        for (GreeterProvider provider : ServiceLoader.load(GreeterProvider.class)) {
            Greeter greeter = provider.create();

            // me.ronygomes.greeter.HelloGreeter - Hello World!
            System.out.printf("%s - %s\n", greeter.getClass().getCanonicalName(), greeter.greet());
        }
    }
}
