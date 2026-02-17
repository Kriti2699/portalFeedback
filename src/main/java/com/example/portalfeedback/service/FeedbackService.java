package com.example.portalfeedback.service;

import com.example.portalfeedback.entity.Feedback;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    Feedback save(Feedback feedback);
    List<Feedback> getAllFeedback();
    Optional<Feedback>getByID(String id);

}
