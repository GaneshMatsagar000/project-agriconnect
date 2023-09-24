package com.agri.agriculture.service;

import java.util.List;

import com.agri.agriculture.dto.CountryDTO;

public interface CountryService {
    List<CountryDTO> getAllCountries();
    CountryDTO getCountryById(Long id);
    CountryDTO createCountry(CountryDTO countryDTO);
    CountryDTO updateCountry(Long id, CountryDTO countryDTO);
    boolean deleteCountry(Long id);
}
