package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"alpha_two_code", "state-province", "web_pages", "name", "domains", "country"})
public class University {

    public String alpha_two_code;
    @JsonProperty("state-province")
    public String stateProvince;
    public List<String> web_pages;
    public String name;
    public List<String> domains;
    public String country;
}
