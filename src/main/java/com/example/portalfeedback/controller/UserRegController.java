package com.example.portalfeedback.controller;

import com.example.portalfeedback.entity.User;
import com.example.portalfeedback.repositoty.UserRegRepo;
import com.example.portalfeedback.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/userReg")
public class UserRegController {

    @Autowired
    private UserRegService userRegService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/userCreate")
    Map<String, Object> createUser(@RequestBody User u) {

        Map<String, Object> response = new HashMap<>();
        u.setCreateon(LocalDateTime.now());
        u.setUpdateon(LocalDateTime.now());
        u.setRole("ROLE_USER");
        User user = new User();

        try {
            //check username/email exist or not
            if (userRegService.existsByUsername(u.getUsername())) {
                response.put("status", "Failure");
                response.put("error", "Username already exists! Please choose another.");
                return response;
            } else if (userRegService.existsByEmail(u.getEmailid())) {
                response.put("status", "Failure");
                response.put("error", "Email already exists! Please choose another.");
                return response;
            } else {
                u.setPassword(passwordEncoder.encode(u.getPassword()));
                response.put("status", "Success");
                response.put("data", userRegService.save(u));

            }
        } catch (Exception e) {
            response.put("status", "Failure");
            response.put("data", e.fillInStackTrace());
        }
        return response;
    }

    @GetMapping("/getAllUser")
    Map<String, Object> getAllUsers() {
        Map<String, Object> response = new HashMap<>();

        try {
            response.put("Status", "Success");
            response.put("Data", userRegService.getAllUser());
        } catch (Exception e) {
            response.put("Status", "Failure");
            response.put("Error", e.getMessage());
        }
        return response;
    }

    @GetMapping("/getUser/{id}")
    Map<String, Object> getUserById(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("Status", "Success");
            response.put("data", userRegService.findById(id));
        } catch (Exception e) {
            response.put("Status", "Failure");
            response.put("Error", e.getMessage());
        }
        return response;


    }


}
