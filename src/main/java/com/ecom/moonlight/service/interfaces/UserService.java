package com.ecom.moonlight.service.interfaces;

public interface UserService {
    String generateAndSendOTP(String username);
    void verifyOTP(String username, String otp);
} 
