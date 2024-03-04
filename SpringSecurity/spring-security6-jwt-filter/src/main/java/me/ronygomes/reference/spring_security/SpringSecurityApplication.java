package me.ronygomes.reference.spring_security;

import me.ronygomes.reference.spring_security.domain.Role;
import me.ronygomes.reference.spring_security.domain.RoleType;
import me.ronygomes.reference.spring_security.dto.RegisterDto;
import me.ronygomes.reference.spring_security.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner {

    private final UserService userService;

    public SpringSecurityApplication(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) {
        userService.saveRole(new Role(RoleType.ADMIN));
        userService.saveRole(new Role(RoleType.USER));

        RegisterDto registerDto = new RegisterDto();
        registerDto.setFirstName("John");
        registerDto.setLastName("Doe");
        registerDto.setPassword("12345");
        registerDto.setEmail("john@doe.com");
        registerDto.setRole("ADMIN");

        userService.register(registerDto);
    }
}
