package com.myorganisation.vibehub.controller;

import com.myorganisation.vibehub.dto.response.CountryResponseDto;
import com.myorganisation.vibehub.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryResponseDto>> getAllCountries() {
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CountryResponseDto>> getCountryPage(
            @RequestParam(defaultValue = "0") Integer pageIndex,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        return new ResponseEntity<>(countryService.getCountryPage(pageIndex, pageSize, sortBy, sortOrder), HttpStatusCode.valueOf(200));
    }
}
