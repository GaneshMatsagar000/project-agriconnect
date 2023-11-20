package com.agri.agriculture.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.VillageDTO;
import com.agri.agriculture.entity.Village;
import com.agri.agriculture.repository.VillageRepository;
import com.agri.agriculture.service.VillageService;

@Service
public class VillageServiceImpl implements VillageService {

	private final VillageRepository villageRepository;

	@Autowired
	public VillageServiceImpl(VillageRepository villageRepository) {
		this.villageRepository = villageRepository;
	}

	@Override
	public List<VillageDTO> getAllVillages() {
		List<Village> villages = villageRepository.findAll();
		return villages.stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public VillageDTO getVillageById(Long id) {
		Village village = villageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Village not found with id: " + id));
		return mapToDTO(village);
	}

	@Override
	public VillageDTO createVillage(VillageDTO villageDTO) {
		Village village = mapToEntity(villageDTO);
		Village savedVillage = villageRepository.save(village);
		return mapToDTO(savedVillage);
	}

	@Override
	public VillageDTO updateVillage(Long id, VillageDTO villageDTO) {
		Village existingVillage = villageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Village not found with id: " + id));

		// Update the fields of existingVillage using data from villageDTO
		existingVillage.setName(villageDTO.getName());
		// Update other fields as needed

		Village updatedVillage = villageRepository.save(existingVillage);
		return mapToDTO(updatedVillage);
	}

	@Override
	public boolean deleteVillage(Long id) {
		try {
			// Check if the village with the given ID exists
			if (!villageRepository.existsById(id)) {
				return false; // Village not found, deletion failed
			}

			// If the village exists, perform the deletion
			villageRepository.deleteById(id);
			return true; // Deletion successful
		} catch (Exception e) {
			// Handle any exceptions that may occur during deletion
			e.printStackTrace(); // You can log the exception for debugging
			return false; // Deletion failed
		}
	}

	// Helper methods to map between Entity and DTO
	private VillageDTO mapToDTO(Village village) {
		VillageDTO dto = new VillageDTO();
		dto.setId(village.getId());
		dto.setName(village.getName());
		// Map other fields as needed
		return dto;
	}

	private Village mapToEntity(VillageDTO villageDTO) {
		Village village = new Village();
		village.setName(villageDTO.getName());
		// Map other fields as needed
		return village;
	}
}
