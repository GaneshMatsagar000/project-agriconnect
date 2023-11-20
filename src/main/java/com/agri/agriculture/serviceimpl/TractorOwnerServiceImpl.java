package com.agri.agriculture.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.TractorOwnerDto;
import com.agri.agriculture.entity.TractorOwner;
import com.agri.agriculture.repository.TractorOwnerRepository;
import com.agri.agriculture.service.TractorOwnerService;

@Service
public class TractorOwnerServiceImpl implements TractorOwnerService {

    private final TractorOwnerRepository tractorOwnerRepository;

    @Autowired
    public TractorOwnerServiceImpl(TractorOwnerRepository tractorOwnerRepository) {
        this.tractorOwnerRepository = tractorOwnerRepository;
    }

    @Override
    public TractorOwner addTractorOwner(TractorOwner tractorOwner) {
        return tractorOwnerRepository.save(tractorOwner);
    }

    @Override
    public List<TractorOwner> getAllTractorOwners() {
        return tractorOwnerRepository.findAll();
    }

    @Override
    public TractorOwner getTractorOwnerById(Long id) {
        Optional<TractorOwner> optionalTractorOwner = tractorOwnerRepository.findById(id);
        return optionalTractorOwner.orElse(null);
    }

    @Override
    public TractorOwner updateTractorOwner(Long id, TractorOwner updatedTractorOwner) {
        // Implement the update logic here, e.g., fetching the existing tractor owner by ID
        // and updating its fields.
        Optional<TractorOwner> optionalTractorOwner = tractorOwnerRepository.findById(id);
        if (optionalTractorOwner.isPresent()) {
            TractorOwner existingTractorOwner = optionalTractorOwner.get();
            // Update the fields of existingTractorOwner with the fields from updatedTractorOwner
            // For example:
            // existingTractorOwner.setOwnerName(updatedTractorOwner.getOwnerName());
            // existingTractorOwner.setTractorName(updatedTractorOwner.getTractorName());
            // ...
            return tractorOwnerRepository.save(existingTractorOwner);
        } else {
            // Handle the case where the tractor owner with the given ID is not found.
            return null;
        }
    }

    @Override
    public void deleteTractorOwner(Long id) {
        tractorOwnerRepository.deleteById(id);
    }

    @Override
    public TractorOwner registerTractorOwner(TractorOwnerDto registrationDto) {
        // Implement logic to register a new tractor owner
        // Perform OTP verification, password hashing, and other necessary checks
        TractorOwner tractorOwner = new TractorOwner();
        tractorOwner.setDriverName(registrationDto.getDriverName());
        tractorOwner.setMobileNumber(registrationDto.getMobileNumber());
        tractorOwner.setPassword(registrationDto.getPassword());
        // Set other fields based on the DTO
        tractorOwner = tractorOwnerRepository.save(tractorOwner);
        return tractorOwner;
    }


    // Implement other service methods as needed
}
