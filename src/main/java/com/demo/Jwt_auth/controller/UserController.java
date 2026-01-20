package com.demo.Jwt_auth.controller;

import com.demo.Jwt_auth.models.User;
import com.demo.Jwt_auth.services.UserServices;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/me")
    public User me(Authentication  authentication) {
        String name = authentication.getName();
        return userServices.findByName(name);
    }
    @DeleteMapping("/user/me")
    public String deleteMyAccount(Authentication authentication) {
        String username = authentication.getName(); // from JWT
        return userServices.deleteMe(username);
    }

}
