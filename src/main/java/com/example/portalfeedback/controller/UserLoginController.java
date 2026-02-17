package com.example.portalfeedback.controller;


import com.example.portalfeedback.entity.User;
import com.example.portalfeedback.jwt.JwtUtil;
import com.example.portalfeedback.repositoty.UserRegRepo;
import com.example.portalfeedback.service.EmailService;
import com.example.portalfeedback.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
/*.............................LOGIN WITH OTP  ............................................*/
    @PostMapping("/send")
    Map<String, Object> sendOtp(@RequestParam String emailid) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> user = userRegRepo.findByEmailid(emailid);
        String otp = otpService.generateOtp(6);
        User u = user.get();
        String data = "";
        try {
            if (emailid == null || emailid.equals("")) {
                data = "email can not be empty";
                response.put("status", "failure");
            } else if (user.isPresent()) {
                emailService.sendEmail(u.getEmailid(), otp);

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

    @PostMapping("/verify-otp")
    Map<String, Object> verifyOtp(@RequestParam String emailid, @RequestParam String otp) {

        String data = "";
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<User> user = userRegRepo.findByEmailid(emailid);
            User u = user.get();
            if (otpService.isOtpExpired(u.getOtpGenerationTime())) {
                data = "OTP expired";
                response.put("status", "Failure");
                response.put("data", data);
            }
            if (u.getOtp().equals(otp)) {

                String token = jwtUtil.generateToken(emailid);
                data = "OTP verified Successfully";
                response.put("status", "Success");
                response.put("data", data);
                response.put("token", token);

            } else {
                data = "Invalid OTP";
                response.put("status", "Failure");
                response.put("data", data);
            }

        } catch (Exception e) {
            response.put("status", "Failure");
            response.put("data", e.fillInStackTrace());
        }

        return response;
    }

    /*...................................LOGIN WITH PASSWORD................................*/

    @PostMapping("/verifyPassword")
    Map<String,Object> loginWithPassword(@RequestParam String emailid,@RequestParam String password) {
        Map<String, Object> response = new HashMap<>();

        try {
            Optional<User> user = userRegRepo.findByEmailid(emailid);
            User u = user.get();
            if (!passwordEncoder.matches(password,u.getPassword())) {
                response.put("status", "Failure");
                response.put("message", "Invalid password");
                return response;
            }
            String token = jwtUtil.generateToken(emailid);
            response.put("status", "Success");
            response.put("data", token);
            response.put("email", emailid);

        }
        catch (Exception e) {
            response.put("status", "Failure");
            response.put("message", e.getMessage());
        }

        return response;
    }
}
