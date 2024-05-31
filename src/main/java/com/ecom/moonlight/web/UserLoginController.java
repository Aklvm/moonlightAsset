package com.ecom.moonlight.web;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.moonlight.dto.CustomerDetail;
import com.ecom.moonlight.dto.EmailUserForLoginAndSignUp;
import com.ecom.moonlight.dto.Response;
import com.ecom.moonlight.dto.UserSignUpLogin;
import com.ecom.moonlight.service.implementation.FindUserProfile;
import com.ecom.moonlight.service.implementation.UserProfileUpdation;
import com.ecom.moonlight.service.implementation.emailImp.EmailLogin;
import com.ecom.moonlight.service.implementation.emailImp.EmailSignUp;
import com.ecom.moonlight.service.implementation.emailImp.EmailSignUpLogin;
import com.ecom.moonlight.service.implementation.emailImp.ForgetPassword;
import com.ecom.moonlight.service.interfaces.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;



@RestController
public class UserLoginController {
    @Autowired
    UserService userService;
    @Autowired
    EmailLogin emailLogin;
    @Autowired
    EmailSignUp emailSignUp;
    @Autowired 
    ForgetPassword forgetPassword;
    @Autowired
    EmailSignUpLogin emailSignUpLogin;
    @Autowired
    Response response;
    @Autowired
    FindUserProfile findUserProfile;
    @Autowired
    UserProfileUpdation userProfileUpdation;

 

    @PostMapping("/otp/gen")
    public ResponseEntity<Response> login(@RequestBody @Valid UserSignUpLogin user) {
        userService.generateAndSendOTP(user.getUserId());
            response.setResult("success");
            response.setMessage("Otp send to mobile number");
            return ResponseEntity.ok().body(response);
        
    }
    @PostMapping("/otp/verify")
    public ResponseEntity<Response> verifyOTP(@RequestBody @Valid UserSignUpLogin user) {
            userService.verifyOTP(user.getUserId() ,user.getPassword());
            response.setResult("success");
            response.setMessage("OTP verified. You are logged in");
            return ResponseEntity.ok(response);
   
    }
    @PostMapping("email/signup")
    public ResponseEntity<Response> signUpViaMail(@RequestBody @Valid EmailUserForLoginAndSignUp user) {
        emailSignUp.createUser(user);
           response.setResult("success");
           response.setMessage("signUp successful");
           return ResponseEntity.ok().body(response); 
        }
    
    @PostMapping("email/login")
    public ResponseEntity<Response> loginViaMail(@RequestBody @Valid EmailUserForLoginAndSignUp user) {
      String jwtToken= emailLogin.loginUser(user);
      response.setResult("success");
      response.setMessage(jwtToken);
      return ResponseEntity.ok().body(response); 
       
    }
    @PostMapping("email/forget")
    public ResponseEntity<Response> updateLoginCredential(@RequestBody @Valid EmailUserForLoginAndSignUp user) {
      forgetPassword.updatePassword(user);
      response.setResult("success");
      response.setMessage("password generated  successful");
      return ResponseEntity.ok().body(response); 
       
    }
    @PostMapping("/userexist")
    public ResponseEntity<Response> userExistOrNot(@RequestBody String emailId,HttpServletRequest request,Principal principal) {
      emailSignUpLogin.userExistOrNot(emailId);
      response.setResult("success");
      response.setMessage("Set your new password");
      return ResponseEntity.ok().body(response); 
    }

    @GetMapping("/getcustomerdetails")
    public ResponseEntity<Response> getCustomerDetails(Principal principal) {
      String user=ControllerUtilty.getUuid();
      response.setResult("success");
      response.setMessage(findUserProfile.getUserDetailById(user));
      return ResponseEntity.ok().body(response); 
      

    }
    @PostMapping("/updatecustomerdetails")
    public ResponseEntity<Response> updateCustomerDetail(@RequestBody @Valid CustomerDetail customerDetail) {
      String user=ControllerUtilty.getUuid();
      userProfileUpdation.updateCustomerDetails(customerDetail,user);
      response.setResult("success");
      response.setMessage("successfully updated");
      return ResponseEntity.ok().body(response); 
      

    }
      

}
