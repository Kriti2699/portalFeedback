package com.example.portalfeedback.impl;

import com.example.portalfeedback.entity.Feedback;
import com.example.portalfeedback.repositoty.FeedbackRepo;
import com.example.portalfeedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackImpl implements FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;
    @Override
    public Feedback save(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }

    @Override
    public Optional<Feedback> getByID(String id) {
        return feedbackRepo.findById(id);
    }
}
