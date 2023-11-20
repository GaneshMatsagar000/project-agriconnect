package com.agri.agriculture.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.DriverDTO;
import com.agri.agriculture.entity.Driver;
import com.agri.agriculture.repository.DriverRepository;
import com.agri.agriculture.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {
	
	@Autowired
	private final DriverRepository driverRepository;
	
	@Autowired
	public DriverServiceImpl(DriverRepository driverRepository) {
		this.driverRepository = driverRepository;
	}

	
	@Override
	public Driver registerDriver(Driver driverDTO) {
	    if (!verifyOTP(driverDTO.getPhoneNumber(), driverDTO.getOtp())) {
	        throw new IllegalArgumentException("Invalid OTP");
	    }
	    
	    // Implement password hashing logic using BCrypt
	    String hashedPassword = hashPassword(driverDTO.getConfirmPassword());
	    driverDTO.setCreatePassword(hashedPassword);
	    driverDTO.setConfirmPassword(hashedPassword);

	    // Save the driver entity and return it
	    return driverRepository.save(driverDTO);
	}

	private String hashedPassword() {
		// TODO Auto-generated method stub
		return null;
	}


	private String hashPassword(String confirmPassword) {
		// TODO Auto-generated method stub
		return null;
	}


	private boolean verifyOTP(String phoneNumber, String otp) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public DriverDTO getDriverById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
    // Implement the methods defined in DriverService

}