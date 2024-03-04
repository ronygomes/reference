package me.ronygomes.reference.spring_security.service;

import jakarta.transaction.Transactional;
import me.ronygomes.reference.spring_security.domain.Role;
import me.ronygomes.reference.spring_security.domain.RoleType;
import me.ronygomes.reference.spring_security.domain.User;
import me.ronygomes.reference.spring_security.dto.BearerToken;
import me.ronygomes.reference.spring_security.dto.LoginDto;
import me.ronygomes.reference.spring_security.dto.RegisterDto;
import me.ronygomes.reference.spring_security.repository.RoleRepository;
import me.ronygomes.reference.spring_security.repository.UserRepository;
import me.ronygomes.reference.spring_security.security.JwtHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtHelper jwtHelper;
    private final AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       JwtHelper jwtHelper,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtHelper = jwtHelper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public BearerToken authenticate(LoginDto l) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(l.getEmail(), l.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return createBearerTokenForUser(user);
    }

    public Optional<BearerToken> register(RegisterDto registerDto) {
        if (userRepository.existsByEmail(registerDto.getEmail())
                || !RoleType.isValidRoleType(registerDto.getRole())) {

            return Optional.empty();
        }

        Role role = roleRepository.findByRoleType(RoleType.findRoleTypeByName(registerDto.getRole()));
        User user = saveUser(registerDto.toUser(passwordEncoder.encode(registerDto.getPassword()), role));

        return Optional.of(createBearerTokenForUser(user));
    }

    private BearerToken createBearerTokenForUser(User user) {
        String token = jwtHelper.generateToken(user.getUsername(),
                user.getRoles().stream()
                        .map(r -> r.getRoleType().name())
                        .toList());

        return new BearerToken(token, "Bearer");
    }
}
