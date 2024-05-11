package com.ecom.moonlight.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ecom.moonlight.dto.Response;
import com.ecom.moonlight.exception.MoonLightException;

@ControllerAdvice
public class HandleExceptionGlobal {
    @Autowired 
    Response response;

    @ExceptionHandler(MoonLightException.class)
    public ResponseEntity<Response> handleMoonlightException(MoonLightException ex) {
        response.setResult("failed");
        response.setMessage(ex.getError());
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(response);
    }
   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<Response> notValidDto(MethodArgumentNotValidException mx){
      BindingResult bindingResult = mx.getBindingResult();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    response.setResult("failed");
    response.setMessage(errors);
    return ResponseEntity.status(HttpStatus.SC_NOT_ACCEPTABLE).body(response);
   }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleUnseenException(Exception ex) {
        response.setResult("failed");
        response.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(response);
    }
}
