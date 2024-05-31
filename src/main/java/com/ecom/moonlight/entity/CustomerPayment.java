package com.ecom.moonlight.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CustomerPayment {
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId ;
    private String paymentType;
    private String cardNumber;
    private String expiry ;
    private String upiId ;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = true)
    private CustomerMaster customerMaster;
 

}
