package com.example.demo.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.ExecutionException;

@ControllerAdvice
public class HandleAsynException {

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<String> handleExecution(ExecutionException e){
        return new ResponseEntity<>("Error while executing", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<String> handleInterrupt(InterruptedException e){
        return new ResponseEntity<>("Searching is interrupted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleSecurity(Exception e){
        return new ResponseEntity<>("Authentication Failed: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleLog(UsernameNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleExpire(ExpiredJwtException e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
