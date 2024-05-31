package com.ecom.moonlight.service.implementation.otpImp;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.moonlight.logging.Mlogger;
import com.ecom.moonlight.service.interfaces.OtpService;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheBuilder;;
@Service
public class OtpServiceImp implements OtpService{
    @Autowired
    OtpAWSSNSImpl otpAWSSNSImpl;
    Mlogger logger = Mlogger.getlogger(OtpServiceImp.class);

    /*
     * Cache to store OTPs
     * Cache key: User ID
     * Cache value: OTP
     */
     private final LoadingCache<String, String> otpCache = CacheBuilder.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES) // Set expiration time for OTP
            .build(new CacheLoader<>() {
                @Override
                public String load(String key) {
                    return null;
                }
            });

    /*
     * Generate a random 6-digit OTP
     * Return the OTP as a string
     */
    @Override
    public String generateOTP() {
        logger.debug("Inside method generateOTP method : execution started: " );
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        logger.debug("Inside method generateOTP method : executed successfully: otp generated  " );
        return String.valueOf(otp);
    }

    /*
     * Retrieve the OTP from the cache for the given user ID.
     *
     * @param userId The user ID for whom the OTP is being retrieved.
     * @return The OTP for the given user ID, or null if the OTP is not found.
     */

    @Override
    public String getOTP(String userId) {
        logger.debug("Inside method getOTP method : execution started: to get otp from cache" );
       String otp = otpCache.getIfPresent(userId);
       logger.debug("Inside method getOTP method : executed successfully: otp found  " );
       return otp;
    }
    
    /*
     * Clear the OTP from the cache for the given user ID.
     *
     * @param userId The user ID for whom the OTP is being cleared.
     */
    @Override
    public void clearOTP(String userId) {
        logger.debug("Inside method clearOTP method : execution started: to clear otp from cache" );
        otpCache.invalidate(userId);
        logger.debug("Inside method clearOTP method : executed successfully: otp cleared  " );
    }
    
       /*
     * Send the OTP to the user via the specified channel (e.g., email or SMS).
     *
     * @param userId The user ID for whom the OTP is being sent.
     * @param otp The OTP to be sent.
     */
    public void sendOTPToUser(String userId,String otp){
        logger.debug("Inside method sendOTPToUser method : execution started: to send otp to user" );
        otpAWSSNSImpl.sendOTP(userId, otp);
        logger.debug("Inside method sendOTPToUser method : executed successfully: otp sent to user  " );

    }
      /*
     * Add the OTP to the cache for the given user ID.
     *
     * @param userId The user ID for whom the OTP is being added.
     * @param otp The OTP to be added.
     */
    @Override
    public void addToCache(String userId,String otp){
        logger.debug("Inside method addToCache method : execution started: to add otp to cache" );
        otpCache.put(userId, otp);
        logger.debug("Inside method addToCache method : executed successfully: otp added to cache  " );
    }
}
