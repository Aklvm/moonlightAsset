package com.ecom.moonlight.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerPaymentDetail {
    private UUID paymentId ;
    private String paymentType;
    private String cardNumber;
    private String expiry ;
    private String upiId ;
}
