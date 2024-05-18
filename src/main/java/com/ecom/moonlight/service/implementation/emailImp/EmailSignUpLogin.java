package com.ecom.moonlight.service.implementation.emailImp;


import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;
import com.ecom.moonlight.logging.Mlogger;
import com.ecom.moonlight.repository.UserRepo;



@Service
public  class EmailSignUpLogin {

    @Autowired
    UserRepo userRepo;

    @Autowired
    LoginCredential user;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()-+=`~\\[\\]{}|;:'\",.<>/?]).{8,}$");
    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    
    Mlogger logger = Mlogger.getlogger(EmailSignUpLogin.class);
    
    public  Optional<LoginCredential> userExistOrNot(String emailId) {
      logger.debug("Inside method userExistOrNot: method execution started:  emailId is {}", emailId);
      Optional<LoginCredential> userWithUserId=userRepo.findById(emailId);
      if(!userWithUserId.isPresent()){
        logger.error("Inside method userExistOrNot: error caught :  user not exist try to login" );
        MoonlightError moonlightError=new MoonlightError("IE_CS_006", "User not exist try to sign up first");
        throw new MoonLightException(moonlightError);
      }
      logger.debug("Inside method userExistOrNot: method executed succesfully: user found with emailId", emailId);
      return userWithUserId;
       
  }

    protected void validatePassword(String password) {
      logger.debug("Inside method validatePassword: method execution started: validation started for password ");
      boolean valid= PASSWORD_PATTERN.matcher(password).matches();
      if(!valid){
        logger.error("Inside method validatePassword: error caught: password combination not in desired format");
        MoonlightError moonlightError=new MoonlightError("IE_CS_005", "password must be combination of capitalLetter SmallLetter digit and specialCharacter");
        throw new MoonLightException(moonlightError);
      }
    }
  
    
}
