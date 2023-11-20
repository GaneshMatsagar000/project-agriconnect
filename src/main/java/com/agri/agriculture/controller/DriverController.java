package com.agri.agriculture.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agriculture.dto.DriverDTO;
import com.agri.agriculture.entity.Driver;
import com.agri.agriculture.service.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
	
	private final DriverService driverService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public DriverController(DriverService driverService, PasswordEncoder passwordEncoder) {
		this.driverService = driverService;
		this.passwordEncoder = passwordEncoder;
	}
	
	// DriverController.java
	// ...

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerDriver(@RequestBody DriverDTO driverDTO) {
	    if (!verifyOTP(driverDTO.getPhoneNumber(), driverDTO.getOtp())) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	            .body(Map.of("success", "false", "message", "Invalid OTP"));
	    }

	    try {
	        Driver driver = createDriverFromDTO(driverDTO);
	        driverService.registerDriver(driver);
	        return ResponseEntity.ok(Map.of("success", "true", "message", "Driver registered successfully"));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(Map.of("success", "false", "message", "Registration failed: " + e.getMessage()));
	    }
	}

	private Driver createDriverFromDTO(DriverDTO driverDTO) {
	    String hashedPassword = hashPassword(driverDTO.getConfirmPassword());

	    Driver driver = new Driver();
	    driver.setFullName(driverDTO.getFullName());
	    driver.setPhoneNumber(driverDTO.getPhoneNumber());
	    driver.setOtp(driverDTO.getOtp());
	    driver.setBirthDate(driverDTO.getBirthDate());
	    driver.setCreatePassword(hashedPassword);
	    driver.setConfirmPassword(hashedPassword);
	    driver.setTractorNumber(driverDTO.getTractorNumber());
	    driver.setCountry(driverDTO.getCountry());
	    driver.setState(driverDTO.getState());
	    driver.setDistrict(driverDTO.getDistrict());
	    driver.setSubdistrict(driverDTO.getSubdistrict());
	    driver.setVillage(driverDTO.getVillage());
	    driver.setPincode(driverDTO.getPincode());

	    return driver;
	}


	// Implement password hashing logic using BCrypt
	private String hashPassword(String confirmPassword) {
	    return passwordEncoder.encode(confirmPassword);
	}

	// ...

	private boolean verifyOTP(String phoneNumber, String otp) {
		// TODO Auto-generated method stub
		return true;
	}

	// Define API endpoints for driver-related operations
}