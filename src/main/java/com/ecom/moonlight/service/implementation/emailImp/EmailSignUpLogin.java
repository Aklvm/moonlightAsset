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
import com.ecom.moonlight.repository.UserRepo;

import jakarta.servlet.http.HttpServletRequest;

@Service
public  class EmailSignUpLogin {

    @Autowired
    UserRepo userRepo;

    @Autowired
    LoginCredential user;

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()-+=`~\\[\\]{}|;:'\",.<>/?]).{8,}$");
    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    

    
    public  Optional<LoginCredential> userExistOrNot(String emailId) {
      Optional<LoginCredential> userWithUserId=userRepo.findById(emailId);
      if(!userWithUserId.isPresent()){
        MoonlightError moonlightError=new MoonlightError("IE_CS_006", "User not exist try to sign up first");
        throw new MoonLightException(moonlightError);
      }
      return userWithUserId;
       
  }

    protected void validatePassword(String password) {
      boolean valid= PASSWORD_PATTERN.matcher(password).matches();
      if(!valid){
        MoonlightError moonlightError=new MoonlightError("IE_CS_005", "password must be combination of capitalLetter SmallLetter digit and specialCharacter");
        throw new MoonLightException(moonlightError);
      }
    }
  
    
}
