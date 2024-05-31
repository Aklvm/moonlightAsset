package com.ecom.moonlight.service.implementation.emailImp;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.moonlight.dto.EmailUserForLoginAndSignUp;
import com.ecom.moonlight.entity.CustomerMaster;
import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;
import com.ecom.moonlight.logging.Mlogger;
import com.ecom.moonlight.repository.CustomerMasterRepo;

@Service
public class EmailSignUp extends EmailSignUpLogin {

    @Autowired
    private CustomerMasterRepo customerMasterRepo;

     Mlogger logger=Mlogger.getlogger(EmailSignUp.class);


    public void createUser(EmailUserForLoginAndSignUp emailUserForLoginAndSignUp){
       logger.debug("Inside method createUser method execution started: EmailUserForLoginAndSignUp dto is {}", emailUserForLoginAndSignUp);
        Optional<LoginCredential> userWithUserId=userRepo.findById(emailUserForLoginAndSignUp.getUserId());
        validatePassword(emailUserForLoginAndSignUp.getPassword());
        if(userWithUserId.isEmpty()){
            String uuid=generateUuid();
            createLoginCredential(emailUserForLoginAndSignUp, uuid);
            createCustomerDetail(emailUserForLoginAndSignUp,uuid);

        }
        else{
            logger.error("Inside method createUser :error caught: User already exist");
            MoonlightError moonlightError=new MoonlightError("IE_CS_003", "User already exist please login");
            throw new MoonLightException(moonlightError);        
          }
          logger.debug("Inside method createUser : method executed succesfully : user created success");
    }

    private void createCustomerDetail(EmailUserForLoginAndSignUp emailUserForLoginAndSignUp, String uuid) {
      logger.debug("Inside method createCustomerDetail : method execution started: EmailUserForLoginAndSignUp dto is {}", emailUserForLoginAndSignUp);
       CustomerMaster customerMaster=new CustomerMaster();
       customerMaster.setUserId(uuid);
       customerMaster.setEmail(emailUserForLoginAndSignUp.getUserId());
       customerMasterRepo.save(customerMaster);
       logger.debug("Inside method createCustomerDetail : method executed succesfully : customer detail saved to Db");

    }

    private void createLoginCredential(EmailUserForLoginAndSignUp emailUserForLoginAndSignUp, String uuid) {
        logger.debug("Inside method createLoginCredential : method excecution started: to save login credential");
        LoginCredential user=new LoginCredential();
        user.setUserId(emailUserForLoginAndSignUp.getUserId());
        user.setPassword(passwordEncoder.encode(emailUserForLoginAndSignUp.getPassword()));
        user.setUuid(uuid);
        if(null==emailUserForLoginAndSignUp.getRole())
        user.setRole("user");
        userRepo.save(user);
        logger.debug("Inside method createLoginCredential : method executed succesfully : login credential saved to Db");

        
    }

    public String generateUuid(){
        logger.debug("Inside method generateUuid : method excecution started: to generate uuid");
        String uuid =UUID.randomUUID().toString();
        logger.debug("Inside method generateUuid : method executed succesfully : uuid generated ");
        return uuid;
    }

 
    


    
}
