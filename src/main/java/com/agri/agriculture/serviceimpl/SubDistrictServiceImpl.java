package com.agri.agriculture.serviceimpl;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.SubDistrictDTO;
import com.agri.agriculture.entity.SubDistrict;
import com.agri.agriculture.repository.SubDistrictRepository;
import com.agri.agriculture.service.SubDistrictService;

@Service
public class SubDistrictServiceImpl implements SubDistrictService {

    @Autowired
    private SubDistrictRepository subDistrictRepository;

    @Override
    public List<SubDistrictDTO> getAllSubDistricts() {
        List<SubDistrict> subDistricts = subDistrictRepository.findAll();
        return subDistricts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public SubDistrictDTO getSubDistrictById(Long id) {
        SubDistrict subDistrict = subDistrictRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubDistrict not found with id: " + id));
        return mapToDTO(subDistrict);
    }

    @Override
    public SubDistrictDTO createSubDistrict(SubDistrictDTO subDistrictDTO) {
        SubDistrict subDistrict = mapToEntity(subDistrictDTO);
        SubDistrict savedSubDistrict = subDistrictRepository.save(subDistrict);
        return mapToDTO(savedSubDistrict);
    }

    @Override
    public SubDistrictDTO updateSubDistrict(Long id, SubDistrictDTO subDistrictDTO) {
        SubDistrict existingSubDistrict = subDistrictRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("SubDistrict not found with id: " + id));

        // Update the fields of existingSubDistrict using data from subDistrictDTO
        existingSubDistrict.setName(subDistrictDTO.getName());
        // Update other fields as needed

        SubDistrict updatedSubDistrict = subDistrictRepository.save(existingSubDistrict);
        return mapToDTO(updatedSubDistrict);
    }

    @Override
    public boolean deleteSubDistrict(Long id) {
        try {
            SubDistrict subDistrict = subDistrictRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("SubDistrict not found with id: " + id));
            subDistrictRepository.delete(subDistrict);
            return true; // Deletion successful
        } catch (Exception e) {
            return false; // Deletion failed
        }
    }


    // Helper methods to map between Entity and DTO
    private SubDistrictDTO mapToDTO(SubDistrict subDistrict) {
        SubDistrictDTO dto = new SubDistrictDTO();
        dto.setId(subDistrict.getId());
        dto.setName(subDistrict.getName());
        // Map other fields as needed
        return dto;
    }

    private SubDistrict mapToEntity(SubDistrictDTO subDistrictDTO) {
        SubDistrict subDistrict = new SubDistrict();
        subDistrict.setName(subDistrictDTO.getName());
        // Map other fields as needed
        return subDistrict;
    }
}
