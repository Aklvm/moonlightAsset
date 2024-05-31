package com.ecom.moonlight.service.implementation.otpImp;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.moonlight.entity.CustomerMaster;
import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;
import com.ecom.moonlight.logging.Mlogger;
import com.ecom.moonlight.repository.CustomerMasterRepo;
import com.ecom.moonlight.repository.UserRepo;
import com.ecom.moonlight.service.interfaces.OtpService;
import com.ecom.moonlight.service.interfaces.UserService;;
@Service
public class UserServiceImp implements UserService {

    @Autowired
    OtpService otpService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CustomerMasterRepo customerMasterRepo;
    private static final Mlogger logger= Mlogger.getlogger(UserServiceImp.class);
   
      /**
     * This method generates a 6-digit OTP and sends it to the user via the specified channel (e.g., email or SMS).
     *
     * @param userId The user ID for whom the OTP is being generated.
     * @return The generated OTP.
     * @throws MoonLightException If an error occurs while generating or sending the OTP.
     */

    public String generateAndSendOTP(String userId) {
     try{
      logger.debug("Inside method generateAndSendOTP method : execution started: with userId: " );
            String otp = otpService.generateOTP();
            // You need to implement a method to send OTP to the user (e.g., via email or SMS)
            otpService.sendOTPToUser(userId, otp);
            otpService.addToCache(userId,otp);
            logger.debug("Inside method generateAndSendOTP method : executed successfully: otp generated and sent to user: " );
            return otp;
     }catch(Exception ex){
      logger.error("Inside method generateAndSendOTP : error caught :Sending otp Failed");
        MoonlightError mnlght =new MoonlightError("IE_CS_001", "Sending otp Failed");
        throw new MoonLightException(mnlght,ex);
     }
 
    }

      /**
     * This method verifies the OTP entered by the user against the OTP stored in the cache.
     *
     * @param userId The user ID for whom the OTP is being verified.
     * @param otp The OTP entered by the user.
     * @throws MoonLightException If the OTP is invalid or has expired.
     */

    public void verifyOTP(String userId, String otp) {
      logger.debug("Inside method verifyOTP method : execution started: with userId and otp: " );
         Optional.ofNullable(otpService.getOTP(userId))
         .filter(otpFromCache -> otp.equals(otpFromCache))
         .ifPresentOrElse(otpFromCache -> {
            otpService.clearOTP(userId);
            createUserIfNotExist(userId);
         }
         , () ->{
            logger.error("Inside method verifyOTP : error caught : otp verification failed");
            MoonlightError mnlght =new MoonlightError("IE_CS_002", "Otp validation failed");
            throw new MoonLightException(mnlght);
         });
         logger.debug("Inside method verifyOTP method : executed successfully: otp verified" );

    }
      /**
     * This method creates a new user account if the user does not already exist.
     *
     * @param userId The user ID for the new account.
     */

    private void createUserIfNotExist(String userId){
      logger.debug("Inside method createUserIfNotExist method : execution started: with userId " );
        Optional<LoginCredential> userExist=userRepo.findById(userId);
        if(userExist.isEmpty()){
                String uuid = generateUuid();
                createLoginCredential(userId,uuid);
                createCustomerDetail(userId,uuid);
                 
        }
        logger.debug("Inside method createUserIfNotExist method : executed successfully: user created" );
    }
       /**
    * This method creates a new customer detail entry in the database.
    *
    * @param userId The user ID for the new customer detail entry.
    * @param uuid The UUID for the new customer detail entry.
    */
   private void createCustomerDetail(String userId, String uuid) {
      logger.debug("Inside method createCustomerDetail method : execution started: with userId and uuid " );
      CustomerMaster customerMaster=new CustomerMaster();
      customerMaster.setUserId(userId);
      customerMaster.setUserId(uuid);
      customerMasterRepo.save(customerMaster);
      logger.debug("Inside method createCustomerDetail method : executed successfully: customer detail created" );
   }
      /**
    * This method generates a new UUID.
    *
    * @return The generated UUID.
    */
   private String generateUuid() {
      logger.debug("Inside method generateUuid method : execution started: " );
        String uuid =UUID.randomUUID().toString();
        logger.debug("Inside method generateUuid method : executed successfully: uuid generated" );
        return uuid;
    }

      /**
    * This method creates a new login credential entry in the database.
    *
    * @param userId The user ID for the new login credential entry.
    * @param uuid The UUID for the new login credential entry.
    */

   private void createLoginCredential(String userId,String uuid){
      logger.debug("Inside method createLoginCredential method : execution started: with userId and uuid " );
       LoginCredential loginCredential=new LoginCredential();
       loginCredential.setUserId(userId);
       loginCredential.setUuid(uuid);
       userRepo.save(loginCredential);
       logger.debug("Inside method createLoginCredential method : executed successfully: login credential created" );
   }
}
