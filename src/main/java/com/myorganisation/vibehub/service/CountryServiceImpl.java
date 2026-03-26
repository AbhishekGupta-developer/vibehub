package com.myorganisation.vibehub.service;

import com.myorganisation.vibehub.dto.response.CountryResponseDto;
import com.myorganisation.vibehub.model.Country;
import com.myorganisation.vibehub.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryResponseDto> getAllCountries() {
        List<Country> countryList = countryRepository.findAll();
        List<CountryResponseDto> countryResponseDtoList = new LinkedList<>();

        for(Country country : countryList) {
            countryResponseDtoList.add(mapCountryToCountryResponseDto(country));
        }

        return countryResponseDtoList;
    }

    @Override
    public Page<CountryResponseDto> getCountryPage(int pageIndex, int pageSize, String sortBy, String sortOrder) {
        Sort sort = (sortOrder.equalsIgnoreCase("ASC")) ?  Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
        Page<Country> countryPage = countryRepository.findAll(pageable);

        Page<CountryResponseDto> countryResponseDtoPage = countryPage.map(country -> mapCountryToCountryResponseDto(country));

        return countryResponseDtoPage;
    }

    private CountryResponseDto mapCountryToCountryResponseDto(Country country) {
        CountryResponseDto countryResponseDto = new CountryResponseDto();
        countryResponseDto.setId(country.getId());
        countryResponseDto.setName(country.getName());
        countryResponseDto.setSlug(country.getSlug());
        countryResponseDto.setCode(country.getCode());

        return countryResponseDto;
    }
}
