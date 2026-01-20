package com.demo.Jwt_auth.controller;

import com.demo.Jwt_auth.models.User;
import com.demo.Jwt_auth.services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminServices adminServices;

    @Autowired
    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody User user) {
        return adminServices.adduser(user);
    }

    @PostMapping("/grantaccess")
    public String grantAccess(@RequestBody User user1){
        return adminServices.grantAdminAccess(user1);
    }

    @PostMapping("/revokeaccess")
    public String revokeAccess(@RequestBody User user1){
       return adminServices.revokeAdminAccess(user1);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestBody User user1){
        return adminServices.DeleteUser(user1);
    }

    @GetMapping("/allusers")
    public List<User> allUsers() {
        return adminServices.allUsers();
    }
}

