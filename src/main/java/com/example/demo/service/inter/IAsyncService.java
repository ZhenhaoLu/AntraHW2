package com.example.demo.service.inter;

import com.example.demo.entity.University;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

public interface IAsyncService {
    CompletableFuture<University[]> getAsync(String country, String paramUrl, RestTemplate restTemplate);
}
