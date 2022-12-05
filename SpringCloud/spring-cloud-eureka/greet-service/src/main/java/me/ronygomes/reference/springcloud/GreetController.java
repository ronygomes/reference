package me.ronygomes.reference.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    private static final String WELCOME_MESSAGE_TEMPLATE = "Hello, %s";

    @GetMapping("/greet/{name}")
    public Map<String, String> greet(@PathVariable String name) {

        Map<String, String> model = new HashMap<>();
        model.put("message", String.format(WELCOME_MESSAGE_TEMPLATE, name));

        return model;
    }
}
