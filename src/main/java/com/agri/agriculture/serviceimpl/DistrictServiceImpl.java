package com.agri.agriculture.serviceimpl;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.DistrictDTO;
import com.agri.agriculture.entity.District;
import com.agri.agriculture.repository.DistrictRepository;
import com.agri.agriculture.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<DistrictDTO> getAllDistricts() {
        List<District> districts = districtRepository.findAll();
        return districts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public DistrictDTO getDistrictById(Long id) {
        District district = districtRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("District not found with id: " + id));
        return mapToDTO(district);
    }

    @Override
    public DistrictDTO createDistrict(DistrictDTO districtDTO) {
        District district = mapToEntity(districtDTO);
        District savedDistrict = districtRepository.save(district);
        return mapToDTO(savedDistrict);
    }

    @Override
    public DistrictDTO updateDistrict(Long id, DistrictDTO districtDTO) {
        District existingDistrict = districtRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("District not found with id: " + id));

        // Update the fields of existingDistrict using data from districtDTO
        existingDistrict.setName(districtDTO.getName());
        // Update other fields as needed

        District updatedDistrict = districtRepository.save(existingDistrict);
        return mapToDTO(updatedDistrict);
    }

    @Override
    public boolean deleteDistrict(Long id) {
        try {
            District district = districtRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("District not found with id: " + id));
            districtRepository.delete(district);
            return true; // Deletion successful
        } catch (Exception e) {
            return false; // Deletion failed
        }
    }

    // Helper methods to map between Entity and DTO
    private DistrictDTO mapToDTO(District district) {
        DistrictDTO dto = new DistrictDTO();
        dto.setId(district.getId());
        dto.setName(district.getName());
        // Map other fields as needed
        return dto;
    }

    private District mapToEntity(DistrictDTO districtDTO) {
        District district = new District();
        district.setName(districtDTO.getName());
        // Map other fields as needed
        return district;
    }
}
