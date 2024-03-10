package com.example.demo.controller;

import com.example.demo.entity.LogRequest;
import com.example.demo.service.inter.IJWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerLogin {

    private final IJWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public ControllerLogin(IJWTService jwtService, AuthenticationManager authenticationManager){
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LogRequest logRequest){
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(logRequest.getUsername(), logRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return new ResponseEntity<>(jwtService.generateToken(logRequest.getUsername()), HttpStatus.OK);
        }else{
            throw new UsernameNotFoundException("Invalid Log Request");
        }
    }
}
