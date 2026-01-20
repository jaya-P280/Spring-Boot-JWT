package com.demo.Jwt_auth.controller;

import com.demo.Jwt_auth.dtos.LoginResponse;
import com.demo.Jwt_auth.dtos.Loginrequest;
import com.demo.Jwt_auth.models.User;
import com.demo.Jwt_auth.repository.UserRepository;
import com.demo.Jwt_auth.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(
            PasswordEncoder passwordEncoder,
            JwtUtils jwtUtils,
            AuthenticationManager authenticationManager,
            UserRepository userRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody Loginrequest request) {
        log.info("Login request for user: {}", request.getUsername());
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationManager.authenticate(authInputToken);

            String token = jwtUtils.generateToken(request.getUsername());
            return new LoginResponse(token);

        } catch (ResponseStatusException ex) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    ex.getMessage()
            );
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        log.info("Signup request for user: {}", user.getUsername());

        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of("USER"));
        userRepository.save(user);
        return "User registered successfully";
    }

}
