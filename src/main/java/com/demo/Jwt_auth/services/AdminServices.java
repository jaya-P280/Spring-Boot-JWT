package com.demo.Jwt_auth.services;

import com.demo.Jwt_auth.models.User;
import com.demo.Jwt_auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdminServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServices(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String adduser(User user1){
        User user = userRepository.findByUsername(user1.getUsername()).orElse(null);
        if(user != null){
            return "Username already exists";
        }
        user1.setRoles(new HashSet<>(Set.of("USER")));
        user1.setPassword(passwordEncoder.encode(user1.getUsername()));
        userRepository.save(user1);

        return "User added successfully";
    }

    public String grantAdminAccess(User user1){
        User user = userRepository.findByUsername(user1.getUsername()).orElse(null);
        if(user != null){
            user.setRoles(new HashSet<>(Set.of("ADMIN")));
            userRepository.save(user);
            return "User granted access";
        }
        return "User not found";
    }

    public String revokeAdminAccess(User user1){
        User user = userRepository.findByUsername(user1.getUsername()).orElse(null);
        if(user != null){
            user.setRoles(new HashSet<>(Set.of("USER")));
            userRepository.save(user);
            return "User revoked access";
        }
        return "User not found";
    }

    public String DeleteUser(User user1){
        User user = userRepository.findByUsername(user1.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
        return "User deleted successfully";
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
