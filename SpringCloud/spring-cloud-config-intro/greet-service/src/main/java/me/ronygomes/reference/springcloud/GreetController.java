package me.ronygomes.reference.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    private Configuration configuratoin;
    
    public GreetController(Configuration configuratoin) {
        this.configuratoin = configuratoin;
    }

    @GetMapping("/greet")
    public Map<String, String> greet() {

        Map<String, String> model = new HashMap<>();
        model.put("message", configuratoin.getWelcomeMessage());

        return model;
    }
}
