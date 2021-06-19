package me.ronygomes.reference.spring_ldap;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/secure")
    public String home(@AuthenticationPrincipal UserExtraInfo user) {
        return String.format("Hello %s, Your Secret Number is: %s " +
                "<br />Use /logout url to Logout", user.getUsername(), user.getExtraInfo());
    }
}
