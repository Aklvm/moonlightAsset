package com.ecom.moonlight.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Response {

    String result;
    Object message;
    public void  setMessage(String message){
        this.message=message;
    }
    public void setMessage(Object obj){
        this.message=obj;
    }
}
