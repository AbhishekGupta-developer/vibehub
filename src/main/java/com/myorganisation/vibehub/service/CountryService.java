package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.response.CountryResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CountryService {
    List<CountryResponseDto> getAllCountries();

    Page<CountryResponseDto> getCountryPage(int pageIndex, int pageSize, String sortBy, String sortOrder);
}
