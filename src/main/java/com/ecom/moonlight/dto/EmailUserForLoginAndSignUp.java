package com.ecom.moonlight.dto;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class EmailUserForLoginAndSignUp {
    @NonNull @Email  
    String userId;

    @NonNull @NotEmpty @Size(min = 8)
    String password;
    
    String role;
    
    
}
