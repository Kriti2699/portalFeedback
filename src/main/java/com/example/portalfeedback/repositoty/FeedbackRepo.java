package com.example.portalfeedback.repositoty;

import com.example.portalfeedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, String> {


    @Override
    Optional<Feedback> findById(String s);
}
