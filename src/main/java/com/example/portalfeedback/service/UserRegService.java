package com.example.portalfeedback.service;


import com.example.portalfeedback.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRegService {

    User save(User user);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean findByUsername(String username);
    List<User> getAllUser();
    Optional<User> getById(String id);

}
