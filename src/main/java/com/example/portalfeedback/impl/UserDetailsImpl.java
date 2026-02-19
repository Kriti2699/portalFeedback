package com.example.portalfeedback.impl;

import com.example.portalfeedback.entity.User;
import com.example.portalfeedback.repositoty.UserRegRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    private UserRegRepo userRegRepo;
    @Override
    public UserDetails loadUserByUsername(String emailid) throws UsernameNotFoundException {
        User u = userRegRepo.findByEmailid(emailid)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with username: " + emailid
                ));

        String role = "ROLE_" + u.getRole();

        return new org.springframework.security.core.userdetails.User(
                u.getEmailid(),
                u.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(role))
        );

    }
}
