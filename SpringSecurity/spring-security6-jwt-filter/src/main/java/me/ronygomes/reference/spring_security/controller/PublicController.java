package me.ronygomes.reference.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/message")
    public Map<String, String> message() {
        System.out.println("Hello World!");
        return Map.of("message", "Welcome to Public Page!");
    }
}
