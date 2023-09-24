package com.agri.agriculture.service;

import com.agri.agriculture.dto.OTPVerificationDTO;
import com.agri.agriculture.dto.UserDTO;
import com.agri.agriculture.dto.UserRegistrationDTO;

public interface UserService {
    UserDTO registerUser(UserRegistrationDTO registrationDTO);
    void sendOTP(String phoneNumber);
    void sendOTP(String phoneNumber, String otp);
	void verifyOTP(OTPVerificationDTO otpVerificationDTO);
}
