package com.agri.agriculture.service;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.TractorOwnerDto;
import com.agri.agriculture.entity.TractorOwner;

@Service
@ComponentScan("com.agri.agriculture")
public interface TractorOwnerService {
	TractorOwner registerTractorOwner(TractorOwnerDto registrationDto);

	List<TractorOwner> getAllTractorOwners();

	TractorOwner getTractorOwnerById(Long id);

	TractorOwner addTractorOwner(TractorOwner tractorOwner);

	TractorOwner updateTractorOwner(Long id, TractorOwner updatedTractorOwner);

	void deleteTractorOwner(Long id);
}
