package me.ronygomes.reference.spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/message")
    public Map<String, String> message() {
        return Map.of("message", "You are an ADMIN!");
    }
}
