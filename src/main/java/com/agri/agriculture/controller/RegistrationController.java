package com.agri.agriculture.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agriculture.ExceptionClass.InvalidOTPException;
import com.agri.agriculture.ExceptionClass.UserNotFoundException;
import com.agri.agriculture.dto.OTPVerificationDTO;
import com.agri.agriculture.dto.UserRegistrationDTO;
import com.agri.agriculture.service.UserService;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ExceptionHandler(InvalidOTPException.class)
    public ResponseEntity<String> handleInvalidOTPException(InvalidOTPException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Invalid OTP\", \"message\": \"" + ex.getMessage() + "\"}");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User not found\", \"message\": \"" + ex.getMessage() + "\"}");
    }

    
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestBody UserRegistrationDTO registrationDTO) {
        try {
            userService.sendOTP(registrationDTO.getPhoneNumber());
            return ResponseEntity.ok("OTP sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending OTP: " + e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Object> verifyOTP(@RequestBody OTPVerificationDTO otpVerificationDTO) {
        if (otpVerificationDTO.getOtp() == null) {
            // Handle the case where 'otp' is null
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Invalid OTP\", \"message\": \"OTP cannot be null.\"}");
        }

        try {
            // Log the incoming OTP for debugging
            System.out.println("Received OTP: " + otpVerificationDTO.getOtp());

            // Rest of your OTP verification logic
            userService.verifyOTP(otpVerificationDTO);
            // Return a JSON response for successful verification
            return ResponseEntity.ok("{\"message\": \"OTP verified successfully\"}");
        } catch (UserNotFoundException e) {
            // Log the exception message for debugging
            System.out.println("UserNotFoundException: " + e.getMessage());

            // Return a JSON response for user not found error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User not found.\", \"message\": \"" + e.getMessage() + "\"}");
        } catch (InvalidOTPException e) {
            // Log the exception message for debugging
            System.out.println("InvalidOTPException: " + e.getMessage());

            // Return a JSON response for invalid OTP error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Invalid OTP\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }

}
