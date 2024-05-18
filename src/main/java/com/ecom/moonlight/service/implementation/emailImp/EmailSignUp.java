package com.ecom.moonlight.service.implementation.emailImp;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecom.moonlight.dto.EmailUserForLoginAndSignUp;
import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;
import com.ecom.moonlight.logging.Mlogger;

@Service
public class EmailSignUp extends EmailSignUpLogin {

     Mlogger logger=Mlogger.getlogger(EmailSignUp.class);


    public void createUser(EmailUserForLoginAndSignUp emailUserForLoginAndSignUp){
        logger.debug("Inside method createUser method execution started: EmailUserForLoginAndSignUp dto is {}", emailUserForLoginAndSignUp);
        Optional<LoginCredential> userWithUserId=userRepo.findById(emailUserForLoginAndSignUp.getUserId());
        validatePassword(emailUserForLoginAndSignUp.getPassword());
        if(userWithUserId.isEmpty()){
           user.setUserId(emailUserForLoginAndSignUp.getUserId());
           user.setPassword(passwordEncoder.encode(emailUserForLoginAndSignUp.getPassword()));
           userRepo.save(user);
        }
        else{
            logger.error("Inside method createUser :error caught: User already exist");
            MoonlightError moonlightError=new MoonlightError("IE_CS_003", "User already exist please login");
            throw new MoonLightException(moonlightError);        
          }
          logger.debug("Inside method createUser : method executed succesfully : user created success");
    }
    
}
