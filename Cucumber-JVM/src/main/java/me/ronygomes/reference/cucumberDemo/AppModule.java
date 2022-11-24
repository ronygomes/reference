package me.ronygomes.reference.cucumberDemo;

import com.google.inject.AbstractModule;

public final class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(GreetService.class).to(GreetServiceImpl.class);
    }
}
