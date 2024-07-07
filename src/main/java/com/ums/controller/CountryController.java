package com.ums.controller;


import com.ums.payload.CountryDto;
import com.ums.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    @Autowired
    private CountryService cservice;

    @PostMapping("/addCountry")
    public String addCountry() {
        return "done";
    }


    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return new ResponseEntity<>(cservice.findAllCountries(), HttpStatus.OK);

    }

}