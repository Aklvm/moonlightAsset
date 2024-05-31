package com.ecom.moonlight.service.implementation.otpImp;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.ecom.moonlight.logging.Mlogger;

import jakarta.annotation.PostConstruct;

@Service

public class OtpAWSSNSImpl {
    @Value("${aws.sns.topicArn}")
    private String topicArn; 
    @Value("${aws.sns.accesskey}")
    private String accessKey; 
    @Value("${aws.sns.secretKey}")
    private String secretKey; 

    @Value("${aws.region")
    private String awsRegion;

    private  AmazonSNS snsClient ;
    Mlogger logger=Mlogger.getlogger(OtpAWSSNSImpl.class);
     
      /**
     * This method initializes the Amazon SNS client.
     */
    @PostConstruct
    public void buildSns(){
        logger.debug("Inside method buildSns method : execution started:");
    AWSCredentials awsCredentials=new BasicAWSCredentials(accessKey, secretKey);
            snsClient= AmazonSNSClientBuilder.standard()
    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
                logger.debug("Inside method buildSns method : executed successfully:");
    
    }
      /**
     * This method sends an OTP to the specified phone number using Amazon SNS.
     *
     * @param phoneNumber The phone number to send the OTP to.
     * @param otp The OTP to send.
     */
    public void sendOTP(String phoneNumber, String otp) {
        logger.debug("Inside method sendOTP method : execution started: going to send OTP to phone number");
        String message = "Your OTP is: " + otp;
        PublishRequest publishRequest = new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber);

        PublishResult result = snsClient.publish(publishRequest);
        logger.debug("Inside method sendOTP method : executed successfully: otp sent to phone number");
    }
}
