package me.ronygomes.reference.springcloud;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("greet-service")
public class Configuration {

    // This will get value from 
    // If Config Server Not Avaiable: application.properties:greet-service.welcome-message 
    // Else http://localhost:8888/greet-service/default
    private String welcomeMessage;

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
}