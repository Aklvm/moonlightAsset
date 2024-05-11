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

@Service
public class OtpAWSSNSImpl {
     
    private String secretKey="qKAlux+g7A8ZIWVTHaYyRd8saNurf3fawGjvMzIC" ;// Get this from your AWS account
  
    private String accesskey="AKIA47PTADAYMBRJBYEH"; // Get this from your AWS account
    @Value("${aws.sns.topicArn}")
    private String topicArn; // Get this from your AWS account


    @Value("${aws.region")
    private String awsRegion;
    AWSCredentials awsCredentials=new BasicAWSCredentials(accesskey, secretKey);
    private final AmazonSNS snsClient = AmazonSNSClientBuilder.standard()
    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    


    public void sendOTP(String phoneNumber, String otp) {
        String message = "Your OTP is: " + otp;

        PublishRequest publishRequest = new PublishRequest()
                .withMessage(message)
                .withPhoneNumber(phoneNumber);

        PublishResult result = snsClient.publish(publishRequest);
        System.out.println("MessageId - " + result.getMessageId());
    }
}
