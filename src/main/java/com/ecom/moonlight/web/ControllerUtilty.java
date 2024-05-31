package com.ecom.moonlight.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ecom.moonlight.entity.LoginCredential;
import com.ecom.moonlight.exception.MoonLightException;
import com.ecom.moonlight.exception.MoonlightError;
import com.ecom.moonlight.logging.Mlogger;

public class ControllerUtilty {

    private static Mlogger logger =Mlogger.getlogger(ControllerUtilty.class) ;

    public static String getUuid(){
      logger.debug("Inside method getUuid : method execution started: going get  uuid for user fom authentication context" );
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication != null && authentication.isAuthenticated()){
      logger.debug("Inside method getUuid : condition is true: authentication is not null and authentication is authenticated" );
     LoginCredential loginCredential = (LoginCredential) authentication.getPrincipal();
     return loginCredential.getUuid();
    }
    else{
      MoonlightError error = new MoonlightError("IE_CS_010", "unauthorized access");
      logger.error("Inside method getUuid : error caught: authentication is null or not authenticated" );
     throw new MoonLightException(error);
    }
}


    
}
