package com.example.demo.controller;

import com.example.demo.entity.University;
//import com.example.demo.service.impl.HipoLabService;
import com.example.demo.service.inter.IHipoLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
public class ControllerHipoLab {

    private final IHipoLabService hipolab;

    @Autowired
    public ControllerHipoLab(IHipoLabService hipolab){
        this.hipolab = hipolab;
    }

    @GetMapping()
    public ResponseEntity<University[]> getAll(){
        return new ResponseEntity<>(hipolab.getAll(), HttpStatus.OK);
    }

    @GetMapping("/complete")
    public ResponseEntity<List<University>> getAll(@RequestParam("country") List<String> countries){
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(hipolab.getByCountries(countries), headers, HttpStatus.OK);
    }

    @GetMapping("/single")
    public ResponseEntity<List<University>> getAllSingle(@RequestParam("country") List<String> countries){
        List<University> res = new ArrayList<>();
        for(String country: countries){
            res.addAll(Arrays.asList(hipolab.getByCountry(country)));
        }
        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json;charset=UTF-8");
        return new ResponseEntity<>(res, headers, HttpStatus.OK);
    }
}
