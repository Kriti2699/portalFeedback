package com.example.portalfeedback.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OTPService {
    public String generateOtp(int length){
        String number="0123456789";
        Random random=new Random();

        StringBuffer otp=new StringBuffer();
        for(int i=0;i<length;i++){
            otp.append(number.charAt(random.nextInt(number.length())));
        }
        return otp.toString();
    }

    public boolean isOtpExpired(long otpGenerationTime) {

        long currentTime=System.currentTimeMillis();
        boolean diff=(currentTime-otpGenerationTime)>2*60*1000;
        return diff;

    }
}
