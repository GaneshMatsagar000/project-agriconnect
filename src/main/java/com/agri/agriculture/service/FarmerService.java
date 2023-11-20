package com.agri.agriculture.service;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.FarmerDto;
import com.agri.agriculture.dto.OTPVerificationDTO;
import com.agri.agriculture.entity.Farmer;

@Service
@ComponentScan("com.agri.agriculture")
public interface FarmerService {
	

	FarmerDto getFarmerById(Long id);

	List<Farmer> getAllFarmers();

	Farmer registerFarmer(Farmer farmer);

	// Delete a farmer by ID
	void deleteFarmer(Long id);

	// Update an existing farmer by ID with the provided data
	Farmer updateFarmer(Long id, Farmer updatedFarmer);

	// Add a new farmer
	Farmer addFarmer(Farmer farmer);

	Farmer saveFarmer(FarmerDto farmerDto);


	Farmer updateFarmer(Long id, FarmerDto updatedFarmerDto);

	Farmer addFarmer(FarmerDto farmerDto);

	void verifyOTP(OTPVerificationDTO otpVerificationDTO);

	Farmer findByUsername(String FullName);
	
	
	
	


}
