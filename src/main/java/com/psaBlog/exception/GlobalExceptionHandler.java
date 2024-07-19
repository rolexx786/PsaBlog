package com.psaBlog.exception;

import com.psaBlog.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handlerResourceNotFound(ResourceNotFound ex){
        ErrorDetails err = new ErrorDetails(ex.getMessage(),new Date());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handlerResourceNotFound(Exception ex){
        ErrorDetails err = new ErrorDetails(ex.getMessage(),new Date());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);

    }

}
