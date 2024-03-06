package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
