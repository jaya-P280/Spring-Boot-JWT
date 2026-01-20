package com.demo.Jwt_auth.services;

import com.demo.Jwt_auth.models.User;
import com.demo.Jwt_auth.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User user(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    public String deleteMe(String username) {
        User user=findByName(username);
        userRepository.delete(user);
        return "Your account has been deleted successfully";
    }

    public User findByName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

}
