package com.ecom.moonlight.service.implementation.emailImp;

import org.springframework.stereotype.Service;

import com.ecom.moonlight.dto.EmailUserForLoginAndSignUp;
import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;

@Service
public class ForgetPassword  extends EmailSignUpLogin{


    public void updatePassword(EmailUserForLoginAndSignUp updateUser) {
         userExistOrNot(updateUser.getUserId())
        .map(LoginCredential::getPassword)
        .filter(pass ->passwordEncoder.matches(updateUser.getPassword(), pass))
        .ifPresent(obj ->{
          MoonlightError moonlightError=new MoonlightError("IE_CS_007", "Password should be diffrent from last password");
          throw new MoonLightException(moonlightError);
        });
        validatePassword(updateUser.getPassword());
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        user.setUserId(updateUser.getUserId());
        userRepo.save(user);
    }
    
}
