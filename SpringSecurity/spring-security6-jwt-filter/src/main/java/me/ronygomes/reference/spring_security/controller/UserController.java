package me.ronygomes.reference.spring_security.controller;

import me.ronygomes.reference.spring_security.dto.BearerToken;
import me.ronygomes.reference.spring_security.dto.LoginDto;
import me.ronygomes.reference.spring_security.dto.RegisterDto;
import me.ronygomes.reference.spring_security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        Optional<BearerToken> registerStatus = userService.register(registerDto);

        if (registerStatus.isPresent()) {
            return new ResponseEntity<>(registerStatus.get(), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Email Exists or invalid data", HttpStatus.SEE_OTHER);
    }

    @PostMapping("/authenticate")
    public BearerToken authenticate(@RequestBody LoginDto loginDto) {
        System.out.println("Hello World! POST");
        return userService.authenticate(loginDto);
    }
}
