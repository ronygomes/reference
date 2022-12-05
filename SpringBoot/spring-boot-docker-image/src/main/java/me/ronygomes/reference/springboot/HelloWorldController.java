package me.ronygomes.reference.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/greet")
    public Map<String, String> helloWorldMap() {
        Map<String, String> m = new HashMap<>();
        m.put("message", "Hello World!");

        return m;
    }
}
