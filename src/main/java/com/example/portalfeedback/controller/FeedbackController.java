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
    private FeedbackService feedbackService;
    @Autowired
    private UserRegRepo userRegRepo;

    @PostMapping("/addFeedback")
    Map<String, Object> save(@RequestBody Map<String, Object> payload) {
        System.out.println("save feedback");

        Map<String, Object> response = new HashMap<>();


        try {
            Feedback feedback = new Feedback();

            boolean isAnonymous = Boolean.TRUE.equals(payload.get("annonymous"));
            feedback.setIs_anonymous(isAnonymous);

            User user = null;

            if (!isAnonymous) {

                // If not anonymous → user_id is required
                if (!payload.containsKey("user_id")) {
                    throw new RuntimeException("user_id is required when not anonymous");
                }

                String userId = payload.get("user_id").toString();

                user = userRegRepo.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));

                feedback.setUser(user);
            } else {
                feedback.setUser(null);
            }


            feedback.setCreateon(LocalDateTime.now());
            feedback.setUpdateon(LocalDateTime.now());
            feedback.setMessage(payload.get("message").toString());
            feedback.setRating(Integer.parseInt(payload.get("rating").toString()));
            feedback.setStatus("0");

            response.put("Status", "Success");
            response.put("data", feedbackService.save(feedback));

        } catch (Exception e) {

            response.put("Status", "Failure");
            response.put("data", e.getMessage());
        }
        return response;
    }
}
