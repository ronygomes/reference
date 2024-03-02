package me.ronygomes.reference.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping(value = {"/", "/index"})
    public String greetingPage() {
        return "index";
    }

    @GetMapping("/private")
    public String privatePage() {
        return "private";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/greet";
    }
}
