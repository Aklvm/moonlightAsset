package com.ecom.moonlight.service.interfaces;

public interface OtpService {
    String generateOTP();
    String getOTP(String userId);
    void clearOTP(String userId);
    void sendOTPToUser(String userId,String otp);
    void addToCache(String userId, String otp);
}
