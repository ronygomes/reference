package me.ronygomes.reference.springcloud;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RestController
public class DashboardController {

    private GreetProxy greetProxy;

    public DashboardController(GreetProxy greetProxy) {
        this.greetProxy = greetProxy;
    }

    @GetMapping("/greet/{name}")
    public Map<String, String> greet(@PathVariable String name) {

        Map<String, String> model = new HashMap<>();
        model.put("greetMessage", greetProxy.generateGreet(name).getMessage());

        return model;
    }

    @GetMapping("/greetRest/{name}")
    public Map<String, String> greetRest(@PathVariable String name) {
        Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("name", name);

        ResponseEntity<Message> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8080/greet/{name}", 
				Message.class, pathVariables);

        Map<String, String> model = new HashMap<>();
        model.put("greetMessage", responseEntity.getBody().getMessage());

        return model;
    }
}
