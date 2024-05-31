package com.ecom.moonlight.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetail {
    
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    private String email;
    @Nullable
    private String phone;
    @Nullable
    private String segment;
    @Nullable
    private String gender;
    @Nullable
    private LocalDateTime dateOfBirth;
    @Nullable
    List<CustomerAddressDetail> customerAddress;
    @Nullable
    List<CustomerPaymentDetail> customerPayment;

}
