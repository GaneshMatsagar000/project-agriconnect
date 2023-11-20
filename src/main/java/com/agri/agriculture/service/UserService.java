package com.agri.agriculture.service;

import com.agri.agriculture.dto.OTPVerificationDTO;
import com.agri.agriculture.dto.UserDTO;

public interface UserService {
    
    void sendOTP(String phoneNumber);
    void sendOTP(String phoneNumber, String otp);
	void verifyOTP(OTPVerificationDTO otpVerificationDTO);
	
	
    UserDTO getUserProfile(Long userId);

	
}
