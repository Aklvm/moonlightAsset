package com.ecom.moonlight.service.implementation.otpImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;
import com.ecom.moonlight.repository.UserRepo;
import com.ecom.moonlight.service.interfaces.OtpService;
import com.ecom.moonlight.service.interfaces.UserService;;
@Service
public class UserServiceImp implements UserService {

    @Autowired
    OtpService otpService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    LoginCredential loginCredential;
    public String generateAndSendOTP(String userId) {
     try{
            String otp = otpService.generateOTP();
            // You need to implement a method to send OTP to the user (e.g., via email or SMS)
            otpService.sendOTPToUser(userId, otp);
            otpService.addToCache(userId,otp);
            return otp;
     }catch(Exception ex){
        MoonlightError mnlght =new MoonlightError("IE_CS_001", "Sending otp Failed");
        throw new MoonLightException(mnlght,ex);
     }
 
    }
    public void verifyOTP(String userId, String otp) {
         Optional.ofNullable(otpService.getOTP(userId))
         .filter(otpFromCache -> otp.equals(otpFromCache))
         .ifPresentOrElse(otpFromCache -> {
            otpService.clearOTP(userId);
            createUserIfNotExist(userId);
         }
         , () ->{
            MoonlightError mnlght =new MoonlightError("IE_CS_002", "Otp validation failed");
            throw new MoonLightException(mnlght);
         });
    }

    private void createUserIfNotExist(String userId){
        Optional<LoginCredential> userExist=userRepo.findById(userId);
        if(userExist.isEmpty()){
                 loginCredential.setUserId(userId);
                 userRepo.save(loginCredential);
        }
    }
}
