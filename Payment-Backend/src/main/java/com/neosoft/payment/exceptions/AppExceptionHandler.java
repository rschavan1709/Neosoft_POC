package com.neosoft.payment.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler(value= {Exception.class, RuntimeException.class})
    public ResponseEntity<String> handleException(Exception ex){
        log.error("Exception in handleException: {}", ex.getMessage());
        String message = ex.getMessage();

        return new ResponseEntity<>(message,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
