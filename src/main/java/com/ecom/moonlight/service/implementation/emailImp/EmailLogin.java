package com.ecom.moonlight.service.implementation.emailImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.moonlight.authOuth.JwtService;
import com.ecom.moonlight.dto.EmailUserForLoginAndSignUp;
import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;
import com.ecom.moonlight.logging.Mlogger;


@Service
public class EmailLogin extends EmailSignUpLogin{

     @Autowired 
    JwtService jwtService;

    Mlogger logger=Mlogger.getlogger(EmailLogin.class);

        public  void loginUser(EmailUserForLoginAndSignUp emailUserForLoginAndSignUp){
        logger.debug("Inside method loginUser method : execution started: EmailUserForLoginAndSignUp dto is {}", emailUserForLoginAndSignUp);
         Optional<LoginCredential> userDetailWithEmailId =userExistOrNot(emailUserForLoginAndSignUp.getUserId());   
         boolean passwordCorrect =passwordEncoder.matches(emailUserForLoginAndSignUp.getPassword(),userDetailWithEmailId.get().getPassword());
          if(!passwordCorrect){
            logger.error("Inside method loginUser : error caught :password is incorrect");
            MoonlightError moonlightError = new MoonlightError("IE_CS_004","Password is wrong");
            throw new MoonLightException(moonlightError);
          }
          jwtService.generateToken(userDetailWithEmailId.get());
          logger.debug("Inside method loginUser : method executed successfully : Login successfull");
          
    }
    
}
