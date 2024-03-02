package me.ronygomes.reference.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/index")
    public String greetingPage() {
        return "index";
    }

    @GetMapping ("/private")
    public String privatePage() {
        return "private";
    }

    @GetMapping ("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping ("/logout")
    public String logout() {
        return "redirect:/greet";
    }
}
