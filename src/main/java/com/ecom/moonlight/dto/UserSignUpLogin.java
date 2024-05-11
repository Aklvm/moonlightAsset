package com.ecom.moonlight.dto;


import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class UserSignUpLogin {
    @NotNull @NotEmpty
    String userId;

    
    String password;
    
    
}
