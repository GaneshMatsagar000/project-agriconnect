package com.agri.agriculture.serviceimpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.ExceptionClass.InvalidOTPException;
import com.agri.agriculture.ExceptionClass.UserNotFoundException;
import com.agri.agriculture.dto.OTPVerificationDTO;
import com.agri.agriculture.dto.UserDTO;
import com.agri.agriculture.entity.User;
import com.agri.agriculture.repository.UserRepository;
import com.agri.agriculture.service.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class UserServiceImpl implements UserService {
	
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	
	

	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
	    this.userRepository = userRepository;
	    this.modelMapper = modelMapper;
	}

	
	@Override
    public UserDTO getUserProfile(Long userId) {
        // Implement user profile retrieval logic here
        // Retrieve the user from the database by userId
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return modelMapper.map(userOptional.get(), UserDTO.class);
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }
        

	@Override
	public void sendOTP(String phoneNumber) {
	    // Generate a random OTP (a 6-digit number)
	    String otp = generateOTP();

	    // Find the user by phone number
	    Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);

	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        user.setOtp(otp); // Set the OTP
	        userRepository.save(user); // Save the user with OTP to the database
	    } else {
	        // If user is not found, create a new user with the provided phone number and OTP
	        User newUser = new User();
	        newUser.setPhoneNumber(phoneNumber);
	        newUser.setOtp(otp);
	        // Set other user properties if needed
	        userRepository.save(newUser);
	    }

	    // Send the OTP via SMS using Twilio
	    try {
	        sendOTP(phoneNumber, otp);
	    } catch (Exception e) {
	        // Handle Twilio sending error
	        throw new RuntimeException("Error sending OTP: " + e.getMessage());
	    }
	}


	@Override
	public void verifyOTP(OTPVerificationDTO otpVerificationDTO) {
		// Find the user by phone number
		Optional<User> userOptional = userRepository.findByPhoneNumber(otpVerificationDTO.getPhoneNumber());

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found.");
		}

		User user = userOptional.get();

		// Check if the provided OTP matches the stored OTP
		if (otpVerificationDTO.getOtp().equals(user.getOtp())) {
			// Correct OTP
			user.setVerified(true);
			userRepository.save(user);
		} else {
			// Incorrect OTP
			throw new InvalidOTPException("Invalid OTP.");
		}
	}

	private String generateOTP() {
		// Generate a random 6-digit OTP
		int otp = (int) (Math.random() * 900000) + 100000;
		return String.valueOf(otp);
	}

	public void sendOTP(String phoneNumber, String otp) {
		
		
	}
}
