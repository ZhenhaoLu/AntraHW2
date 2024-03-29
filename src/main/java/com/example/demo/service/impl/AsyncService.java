package com.example.demo.service.impl;

import com.example.demo.entity.University;
import com.example.demo.service.inter.IAsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService implements IAsyncService {

    @Async
    public CompletableFuture<University[]> getAsync(String country, String paramUrl, RestTemplate restTemplate){
        String url = paramUrl + country;
        University[] result = restTemplate.getForObject(url, University[].class);
        System.out.println(Thread.currentThread());
        return CompletableFuture.completedFuture(result);
    }

}
