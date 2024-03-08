package com.example.demo.controller;

import com.example.demo.service.HipoLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Get All Countries
 *      url: /universities
 *      method: get
 *      status: 200, 500
 *      response: list of universities
 * Get All By One or More Countries(Single Thread)
 *      url: /universities/single?country={country_1}[&country={country_i}]
 *      method: get
 *      status: 200, 500
 *      response: list of universities
 * Get All By One or More Countries(CompletableFuture)
 *      url: /universities/complete?country={country_1}[&country={country_i}]
 *      method: get
 *      status: 200, 500
 *      response: list of universities
 *
 */
@RestController
@RequestMapping("/universities")
public class Controller1 {

    private final HipoLabService hipolab;

    @Autowired
    public Controller1(HipoLabService hipolab){
        this.hipolab = hipolab;
    }

    @GetMapping()
    public ResponseEntity<String> getAll(){
        return hipolab.getAll();
//        return new ResponseEntity<>("Hello, world", HttpStatus.OK);
    }

    @GetMapping("/complete")
    public ResponseEntity<String> getAll(@RequestParam("country") List<String> countries) throws ExecutionException, InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(hipolab.getByCountries(countries), headers, HttpStatus.OK);
    }

    @GetMapping("/single")
    public ResponseEntity<String> getAllSingle(@RequestParam("country") List<String> countries){
        StringBuilder sb = new StringBuilder();
        for(String country: countries){
            if(sb.isEmpty()){
                sb.append(hipolab.getByCountry(country));
            }else{
                sb.deleteCharAt(sb.length() - 1).append(", ").
                        append(hipolab.getByCountry(country).substring(1));
            }
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(sb.toString(), headers, HttpStatus.OK);
    }
}
