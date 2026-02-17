package com.example.portalfeedback.controller;


import com.example.portalfeedback.entity.User;
import com.example.portalfeedback.jwt.JwtUtil;
import com.example.portalfeedback.repositoty.UserRegRepo;
import com.example.portalfeedback.service.EmailService;
import com.example.portalfeedback.service.OTPService;
import com.example.portalfeedback.service.UserRegService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/login")
public class UserLoginController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmailService emailService;
    @Autowired
    private OTPService otpService;
    @Autowired
    private UserRegRepo userRegRepo;

    @PostMapping("/send")
    Map<String, Object> sendOtp(@RequestParam String emailid) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> user = userRegRepo.findByEmailid(emailid);
        String otp= otpService.generateOtp(6);
        User u=user.get();
        String data = "";
        try {
            if (emailid == null || emailid.equals("")) {
                data = "email can not be empty";
                response.put("status", "failure");
            } else if (user.isPresent()) {

                u.setPassword(emailid);
                u.setOtp(otp);
                u.setOtpGenerationTime(System.currentTimeMillis());
                u.setUpdateon(LocalDateTime.now());
                userRegRepo.save(u);
                data = otp;
                response.put("status", "success");
                response.put("data", data);
            }
        } catch (Exception e) {
            response.put("status", "Failure");
            response.put("data", e.fillInStackTrace());
        }
        return response;
    }

}
