package com.agri.agriculture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agriculture.ExceptionClass.InvalidOTPException;
import com.agri.agriculture.ExceptionClass.UserNotFoundException;
import com.agri.agriculture.dto.FarmerDto;
import com.agri.agriculture.entity.Farmer;
import com.agri.agriculture.service.FarmerService;

@RestController
@RequestMapping(path = "/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FarmerController(FarmerService farmerService, PasswordEncoder passwordEncoder) {
        this.farmerService = farmerService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @ExceptionHandler(InvalidOTPException.class)
    public ResponseEntity<String> handleInvalidOTPException(InvalidOTPException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Invalid OTP\", \"message\": \"" + ex.getMessage() + "\"}");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User not found\", \"message\": \"" + ex.getMessage() + "\"}");
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerFarmer(@RequestBody FarmerDto farmerDto) {
        // Verify OTP (you should implement this logic)
    	 System.out.println("Received farmerDto: " + farmerDto.toString());
        if (!verifyOTP(farmerDto.getPhoneNumber(), farmerDto.getOtp())) {
            return ResponseEntity.badRequest().body("Invalid OTP");
        }

        // Hash the password securely using BCrypt
        String hashedPassword = hashPassword(farmerDto.getPassword());

        Farmer farmer = new Farmer();
        farmer.setFullName(farmerDto.getFullName());
        farmer.setFarmAddress(farmerDto.getFarmAddress());
        farmer.setWorkDetails(farmerDto.getWorkDetails());
        farmer.setPaymentMode(farmerDto.getPaymentMode());
        farmer.setPhoneNumber(farmerDto.getPhoneNumber());
        farmer.setPassword(hashedPassword);
        farmer.setCountry(farmerDto.getCountry());
        farmer.setState(farmerDto.getState());
        farmer.setDistrict(farmerDto.getDistrict());
        farmer.setSubdistrict(farmerDto.getSubdistrict());
        farmer.setVillage(farmerDto.getVillage());
        farmer.setPincode(farmerDto.getPincode());

        // Save the farmer in the database (you should handle exceptions)
        farmerService.registerFarmer(farmer);

        return ResponseEntity.ok("Farmer registered successfully");
    }

    private String hashPassword(String password) {
        // Implement your password hashing logic here (e.g., using passwordEncoder)
        return passwordEncoder.encode(password);
    }

    private boolean verifyOTP(String phoneNumber, String otp) {
        // Implement your OTP verification logic here
        // For example, compare the provided OTP with the stored OTP
        // and return true if they match; otherwise, return false.
        // You can use a service or library to send and verify OTPs.
        // For demonstration purposes, we'll assume OTP verification is successful.
        return true;
    }
    @GetMapping("/all")
    public List<Farmer> getAllFarmers() {
        // Call the farmerService to retrieve all farmers
        return farmerService.getAllFarmers();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFarmer(@RequestBody FarmerDto farmerDto) {
        // Create a Farmer entity from the DTO
        Farmer farmer = new Farmer();
        farmer.setFullName(farmerDto.getFullName());
        farmer.setFarmAddress(farmerDto.getFarmAddress());
        farmer.setWorkDetails(farmerDto.getWorkDetails());
        farmer.setPaymentMode(farmerDto.getPaymentMode());
        farmer.setPhoneNumber(farmerDto.getPhoneNumber());
        farmer.setCountry(farmerDto.getCountry());
        farmer.setState(farmerDto.getState());
        farmer.setDistrict(farmerDto.getDistrict());
        farmer.setSubdistrict(farmerDto.getSubdistrict());
        farmer.setVillage(farmerDto.getVillage());
        farmer.setPincode(farmerDto.getPincode());

        Farmer savedFarmer = farmerService.saveFarmer(farmerDto);
        if (savedFarmer != null) {
            return ResponseEntity.ok("Farmer added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add farmer");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody FarmerDto updatedFarmerDto) {
        // Get the existing farmer from the service
        FarmerDto existingFarmer = farmerService.getFarmerById(id);
        if (existingFarmer == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the existing farmer entity with the new data
        existingFarmer.setFullName(updatedFarmerDto.getFullName());
        existingFarmer.setFarmAddress(updatedFarmerDto.getFarmAddress());
        existingFarmer.setWorkDetails(updatedFarmerDto.getWorkDetails());
        existingFarmer.setPaymentMode(updatedFarmerDto.getPaymentMode());
        existingFarmer.setPhoneNumber(updatedFarmerDto.getPhoneNumber());
        existingFarmer.setCountry(updatedFarmerDto.getCountry());
        existingFarmer.setState(updatedFarmerDto.getState());
        existingFarmer.setDistrict(updatedFarmerDto.getDistrict());
        existingFarmer.setSubdistrict(updatedFarmerDto.getSubdistrict());
        existingFarmer.setVillage(updatedFarmerDto.getVillage());
        existingFarmer.setPincode(updatedFarmerDto.getPincode());

        // Save the updated farmer in the database
        Farmer updatedFarmer = farmerService.updateFarmer(id, existingFarmer);
        return ResponseEntity.ok(updatedFarmer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return ResponseEntity.ok("Farmer deleted successfully");
    }
}
