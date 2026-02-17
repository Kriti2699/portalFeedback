package com.example.portalfeedback.controller;

import com.example.portalfeedback.entity.Feedback;
import com.example.portalfeedback.entity.User;
import com.example.portalfeedback.repositoty.FeedbackRepo;
import com.example.portalfeedback.repositoty.UserRegRepo;
import com.example.portalfeedback.service.FeedbackService;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepo feedbackRepo;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserRegRepo userRegRepo;

    @PostMapping("/addFeedback")
    Map<String, Object> save(@RequestBody Map<String, Object> payload) {

        Map<String, Object> response = new HashMap<>();

        try {
            String userId = payload.get("user_id").toString();
            User user=userRegRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found") );

            Feedback feedback=new Feedback();

            feedback.setCreateon(LocalDateTime.now());
            feedback.setUpdateon(LocalDateTime.now());
            feedback.setMessage(payload.get("message").toString());
            feedback.setRating((Integer) payload.get("rating"));
            feedback.setStatus("0");
            feedback.setIs_anonymous(Boolean.parseBoolean((String) payload.get("annonymous")));

            response.put("Status", "Success");
            response.put("data",feedbackService.save(feedback));

        } catch (Exception e) {

            response.put("Status", "Failure");
            response.put("data",e.getMessage());        }
        return response;
    }
}
