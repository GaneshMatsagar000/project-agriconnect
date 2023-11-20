package com.agri.agriculture.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.CountryDTO;
import com.agri.agriculture.entity.Country;
import com.agri.agriculture.repository.CountryRepository;
import com.agri.agriculture.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryDTO> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CountryDTO getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));
        return mapToDTO(country);
    }

    @Override
    public CountryDTO createCountry(CountryDTO countryDTO) {
        Country country = mapToEntity(countryDTO);
        Country savedCountry = countryRepository.save(country);
        return mapToDTO(savedCountry);
    }

    @Override
    public CountryDTO updateCountry(Long id, CountryDTO countryDTO) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));

        // Update the fields of existingCountry using data from countryDTO
        existingCountry.setName(countryDTO.getName());
        // Update other fields as needed

        Country updatedCountry = countryRepository.save(existingCountry);
        return mapToDTO(updatedCountry);
    }

    @Override
    public boolean deleteCountry(Long id) {
        try {
            Country country = countryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));
            countryRepository.delete(country);
            return true; // Deletion successful
        } catch (Exception e) {
            return false; // Deletion failed
        }
    }


    // Helper methods to map between Entity and DTO
    private CountryDTO mapToDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        // Map other fields as needed
        return dto;
    }

    private Country mapToEntity(CountryDTO countryDTO) {
        Country country = new Country();
        country.setName(countryDTO.getName());
        // Map other fields as needed
        return country;
    }
}
