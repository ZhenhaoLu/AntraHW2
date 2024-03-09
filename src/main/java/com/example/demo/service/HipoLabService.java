package com.example.demo.service;

import com.example.demo.entity.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class HipoLabService {

    private final RestTemplate restTemplate;
//    private final AsyncService asyncService;
    @Value("${api.baseUrl}")
    private String BASE_URL;
    @Value("${api.baseUrl.country}")
    private String PARAM_URL;

    @Autowired
    public HipoLabService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
//        this.asyncService = asyncService;
    }

    public University[] getAll(){
        return restTemplate.getForObject(BASE_URL, University[].class);
    }

    public List<University> getByCountries(List<String> countries){
        List<CompletableFuture<University[]>> tasks = new ArrayList<>();
        for(String country: countries){
            tasks.add(CompletableFuture.supplyAsync(() -> getByCountry(country)));
//            tasks.add(asyncService.getAsync(country, PARAM_URL, this.restTemplate));
        }
        CompletableFuture<Void> allOf = CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0]));
        allOf.join();
        List<University> res = new ArrayList<>();
        for (CompletableFuture<University[]> task : tasks) {
            res.addAll(Arrays.asList(task.join()));
        }
        return res;
    }

    public University[] getByCountry(String country){
        String url = PARAM_URL + country;
        return restTemplate.getForObject(url, University[].class);
    }
}
