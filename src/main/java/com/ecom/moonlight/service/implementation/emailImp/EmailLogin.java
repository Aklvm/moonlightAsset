package com.ecom.moonlight.service.implementation.emailImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.moonlight.authOuth.JwtService;
import com.ecom.moonlight.dto.EmailUserForLoginAndSignUp;
import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;


@Service
public class EmailLogin extends EmailSignUpLogin{
     @Autowired 
    JwtService jwtService;

        public  void loginUser(EmailUserForLoginAndSignUp emailUserForLoginAndSignUp){
         Optional<LoginCredential> userDetailWithEmailId =userExistOrNot(emailUserForLoginAndSignUp.getUserId());   
         boolean passwordCorrect =passwordEncoder.matches(emailUserForLoginAndSignUp.getPassword(),userDetailWithEmailId.get().getPassword());
          if(!passwordCorrect){
            MoonlightError moonlightError = new MoonlightError("IE_CS_004","Password is wrong");
            throw new MoonLightException(moonlightError);
          }
          jwtService.generateToken(userDetailWithEmailId.get());
          
    }
    
}
