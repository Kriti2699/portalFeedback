package com.example.portalfeedback.impl;

import com.example.portalfeedback.entity.User;
import com.example.portalfeedback.repositoty.UserRegRepo;
import com.example.portalfeedback.service.UserRegService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRegImpl implements UserRegService {

    private UserRegRepo userRegRepo;
    @Override
    public User save(User user) {
        return userRegRepo.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRegRepo.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRegRepo.existsByEmail(email);
    }

    @Override
    public boolean findByUsername(String username) {
        return userRegRepo.existsByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return List.of();
    }


}
