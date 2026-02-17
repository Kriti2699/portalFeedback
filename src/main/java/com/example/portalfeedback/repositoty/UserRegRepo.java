package com.example.portalfeedback.repositoty;

import com.example.portalfeedback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegRepo extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmailid(String emailid);
    Optional<User> findByEmailid(String emailid);



}
