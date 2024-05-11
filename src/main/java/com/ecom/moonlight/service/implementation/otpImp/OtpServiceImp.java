package com.ecom.moonlight.service.implementation.otpImp;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.moonlight.service.interfaces.OtpService;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheBuilder;;
@Service
public class OtpServiceImp implements OtpService{
    @Autowired
    OtpAWSSNSImpl otpAWSSNSImpl;

     private final LoadingCache<String, String> otpCache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES) // Set expiration time for OTP
            .build(new CacheLoader<>() {
                @Override
                public String load(String key) {
                    return null;
                }
            });
    @Override
    public String generateOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public String getOTP(String userId) {
        return otpCache.getIfPresent(userId);
    }

    @Override
    public void clearOTP(String userId) {
        otpCache.invalidate(userId);
    }
    
    public void sendOTPToUser(String userId,String otp){
        otpAWSSNSImpl.sendOTP(userId, otp);

    }
    @Override
    public void addToCache(String userId,String otp){
        otpCache.put(userId, otp);
    }
}
