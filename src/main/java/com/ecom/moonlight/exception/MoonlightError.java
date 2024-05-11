package com.ecom.moonlight.exception;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MoonlightError {

    String errorCode;
    String errorMessage;

    public MoonlightError(String errorCode,String errorMessage){
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }

}
