package com.example.portalfeedback.impl;

import com.example.portalfeedback.entity.User;
import com.example.portalfeedback.repositoty.UserRegRepo;
import com.example.portalfeedback.service.UserRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRegImpl implements UserRegService {

    @Autowired
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
    public boolean existsByEmail(String emailid) {
        return userRegRepo.existsByEmailid(emailid);
    }

    @Override
    public boolean findByUsername(String username) {
        return userRegRepo.existsByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return userRegRepo.findAll();
    }

    @Override
    public Optional<User> getById(String id) {
        return userRegRepo.findById(id);
    }


}
