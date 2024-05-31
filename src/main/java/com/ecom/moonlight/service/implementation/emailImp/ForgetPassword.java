package com.ecom.moonlight.service.implementation.emailImp;

import org.springframework.stereotype.Service;

import com.ecom.moonlight.dto.EmailUserForLoginAndSignUp;
import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;
import com.ecom.moonlight.logging.Mlogger;

@Service
public class ForgetPassword  extends EmailSignUpLogin{

  Mlogger logger=Mlogger.getlogger(ForgetPassword.class);


    public void updatePassword(EmailUserForLoginAndSignUp updateUser) {
      logger.debug("Inside method updatePassword: method execution started: EmailUserForLoginAndSignUp dto is {}", updateUser);
      LoginCredential user=new LoginCredential();
         userExistOrNot(updateUser.getUserId())
        .map(LoginCredential::getPassword)
        .filter(pass ->passwordEncoder.matches(updateUser.getPassword(), pass))
        .ifPresent(obj ->{
          logger.error("Inside method updatePassword: Error caught : Password should be different from last password");
          MoonlightError moonlightError=new MoonlightError("IE_CS_007", "Password should be diffrent from last password");
          throw new MoonLightException(moonlightError);
        });
        validatePassword(updateUser.getPassword());
        user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        user.setUserId(updateUser.getUserId());
        userRepo.save(user);
        logger.debug("Inside method updatePassword: method executed sucesfully: password reset success");
    }
    
}
