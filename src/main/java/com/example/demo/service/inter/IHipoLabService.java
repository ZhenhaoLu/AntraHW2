package com.example.demo.service.inter;

import com.example.demo.entity.University;

import java.util.List;

public interface IHipoLabService {

    University[] getAll();
    List<University> getByCountries(List<String> countries);
    University[] getByCountry(String country);
}
