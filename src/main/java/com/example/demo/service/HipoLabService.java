package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class HipoLabService {

    private final RestTemplate restTemplate;

    @Autowired
    public HipoLabService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getAll(){
        return restTemplate.getForEntity("http://universities.hipolabs.com/search", String.class);
    }

    public String getByCountries(List<String> countries) throws ExecutionException, InterruptedException {
        List<CompletableFuture<String>> tasks = new ArrayList<>();
        for(String country: countries){
            tasks.add(getAsync(country));
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));
        allOf.get();
        StringBuilder sb = new StringBuilder(tasks.get(0).get());
        for(int i = 1; i < tasks.size(); i++){
            String newAns = tasks.get(i).get();
            sb.deleteCharAt(sb.length() - 1).append(", ").append(newAns.substring(1));
        }
        return sb.toString();
    }

    @Async
    public CompletableFuture<String> getAsync(String country){
        String result = getByCountry(country);
        return CompletableFuture.completedFuture(result);
    }

    public String getByCountry(String country){
        String url = "http://universities.hipolabs.com/search?country=" + country;
        return restTemplate.getForEntity(url, String.class).getBody();
    }
}
