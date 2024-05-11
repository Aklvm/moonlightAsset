package com.ecom.moonlight.service.implementation.emailImp;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.moonlight.dto.EmailUserForLoginAndSignUp;
import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;

@Service
public class EmailSignUp extends EmailSignUpLogin {


    public void createUser(EmailUserForLoginAndSignUp emailUserForLoginAndSignUp){
        Optional<LoginCredential> userWithUserId=userRepo.findById(emailUserForLoginAndSignUp.getUserId());
        validatePassword(emailUserForLoginAndSignUp.getPassword());
        if(userWithUserId.isEmpty()){
           user.setUserId(emailUserForLoginAndSignUp.getUserId());
           user.setPassword(passwordEncoder.encode(emailUserForLoginAndSignUp.getPassword()));
           userRepo.save(user);
        }
        else{
            MoonlightError moonlightError=new MoonlightError("IE_CS_003", "User already exist please login");
            throw new MoonLightException(moonlightError);        
          }
    }
    
}
