package com.agri.agriculture.serviceimpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.dto.FarmerDto;
import com.agri.agriculture.dto.OTPVerificationDTO;
import com.agri.agriculture.entity.Farmer;
import com.agri.agriculture.repository.FarmerRepository;
import com.agri.agriculture.service.FarmerService;

@Service
public class FarmerServiceImpl implements FarmerService {
	
	@Autowired
    private final FarmerRepository farmerRepository;

    @Autowired
    public FarmerServiceImpl(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @Override
    public Farmer registerFarmer(FarmerDto farmerDto) {
        // Verify OTP (you should implement this logic)
        if (!verifyOTP(farmerDto.getPhoneNumber(), farmerDto.getOtp())) {
            throw new IllegalArgumentException("Invalid OTP");
        }
        // Hash the password securely using BCrypt (you should implement this logic)
        String hashedPassword = hashPassword(farmerDto.getPassword());

        // Create a new Farmer entity
        Farmer farmer = new Farmer();
        farmer.setFullName(farmerDto.getFullName());
        farmer.setPhoneNumber(farmerDto.getPhoneNumber());
        farmer.setOtp(farmerDto.getOtp());
        farmer.setPassword(hashedPassword);

        // Set other fields from the DTO to the entity
        farmer.setFarmAddress(farmerDto.getFarmAddress());
        farmer.setWorkDetails(farmerDto.getWorkDetails());
        farmer.setPaymentMode(farmerDto.getPaymentMode());
        farmer.setCountry(farmerDto.getCountry());
        farmer.setState(farmerDto.getState());
        farmer.setDistrict(farmerDto.getDistrict());
        farmer.setSubdistrict(farmerDto.getSubdistrict());
        farmer.setVillage(farmerDto.getVillage());
        farmer.setPincode(farmerDto.getPincode());

        // Save the farmer in the database
        return farmerRepository.save(farmer);
    }

    private boolean verifyOTP(String phoneNumber, String otp) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
    public List<Farmer> getAllFarmers() {
        return (List<Farmer>) farmerRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteFarmer(Long id) {
        farmerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Farmer updateFarmer(Long id, Farmer updatedFarmer) {
        Optional<Farmer> existingFarmerOptional = farmerRepository.findById(id);

        if (existingFarmerOptional.isPresent()) {
            Farmer existingFarmer = existingFarmerOptional.get();
            existingFarmer.setFullName(updatedFarmer.getFullName());
            existingFarmer.setFarmAddress(updatedFarmer.getFarmAddress());
            existingFarmer.setWorkDetails(updatedFarmer.getWorkDetails());
            existingFarmer.setPaymentMode(updatedFarmer.getPaymentMode());
            existingFarmer.setPhoneNumber(updatedFarmer.getPhoneNumber());

            // Set other fields as needed

            return farmerRepository.save(existingFarmer);
        }

        return null; // Return null if the farmer with the given ID is not found
    }

    @Override
    @Transactional
    public Farmer addFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    @Override
    public Farmer saveFarmer(FarmerDto farmerDto) {
        Farmer farmer = new Farmer();

        // Set fields from the DTO to the entity
        farmer.setFullName(farmerDto.getFullName());
        farmer.setFarmAddress(farmerDto.getFarmAddress());
        farmer.setWorkDetails(farmerDto.getWorkDetails());
        farmer.setPaymentMode(farmerDto.getPaymentMode());
        farmer.setPhoneNumber(farmerDto.getPhoneNumber());
        farmer.setOtp(farmerDto.getOtp());
        farmer.setPassword(farmerDto.getPassword());
        farmer.setCountry(farmerDto.getCountry());
        farmer.setState(farmerDto.getState());
        farmer.setDistrict(farmerDto.getDistrict());
        farmer.setSubdistrict(farmerDto.getSubdistrict());
        farmer.setVillage(farmerDto.getVillage());
        farmer.setPincode(farmerDto.getPincode());

        // Set other fields as needed

        return farmerRepository.save(farmer);
    }

    // Implement OTP verification logic using Twilio
//    private boolean verifyOTP(String phoneNumber, String otp) {
//        try {
//            // Initialize Twilio with your credentials
//            Twilio.init("AC5c002106437e3daaa77edc0d37a414ac", "b64ce793471306cc2eea249c00628388");
//
//            Verification verification = Verification.creator("VA79ff52335eedc06f93d751c1dfd783fb")
//            	    .to(phoneNumber)
//            	    .channel("sms")
//            	    .create();
//
//
//
//            // You can set the code if needed (e.g., for testing purposes)
//            // verification.setCode("123456");
//
//            VerificationCheck check = VerificationCheck.creator("VA79ff52335eedc06f93d751c1dfd783fb", verification.getSid())
//                    .setCode(otp)
//                    .create();
//
//            return "approved".equals(check.getStatus());
//        } catch (ApiException e) {
//            // Handle exceptions, e.g., invalid phone number, rate limits, etc.
//            e.printStackTrace();
//            return false;
//        }
//    }

    // Implement password hashing logic using BCrypt (you should implement this logic)
    private String hashPassword(String password) {
        // Use BCryptPasswordEncoder or another secure method to hash the password
        // Implement this logic as needed
        return password; // For demonstration purposes, we return the original password
    }

    @Override
    public FarmerDto getFarmerById(Long id) {
        // Implement this method to retrieve and map a Farmer entity to a FarmerDto
        // Return null or throw an exception if the farmer with the given ID is not found
        return null;
    }

    @Override
    public Farmer updateFarmer(Long id, FarmerDto updatedFarmerDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Farmer addFarmer(FarmerDto farmerDto) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public void verifyOTP(OTPVerificationDTO otpVerificationDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerFarmer(Farmer farmer) {
		// TODO Auto-generated method stub
		
	}
}
