package com.agri.agriculture.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agriculture.dto.FarmerDto;
import com.agri.agriculture.dto.UserDTO;
import com.agri.agriculture.entity.Farmer;
import com.agri.agriculture.service.FarmerService;

@RestController
@RequestMapping(path = "/api/farmers") //working do not change 
public class FarmerController {

	private final FarmerService farmerService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public FarmerController(FarmerService farmerService, PasswordEncoder passwordEncoder) {
		this.farmerService = farmerService;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerFarmer(@RequestBody FarmerDto farmerDto) {
		// Verify OTP (you should implement this logic)
		if (!verifyOTP(farmerDto.getPhoneNumber(), farmerDto.getOtp())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Map.of("success", "false", "message", "Invalid OTP"));
		}

		// Hash the password securely using BCrypt
		String hashedPassword = hashPassword(farmerDto.getPassword());

		Farmer farmer = new Farmer();
		farmer.setName(farmerDto.getName());
		farmer.setSurname(farmerDto.getSurname());
		farmer.setCountry(farmerDto.getCountry());
		farmer.setState(farmerDto.getState());
		farmer.setDistrict(farmerDto.getDistrict());
		farmer.setSubdistrict(farmerDto.getSubDistrict());
		farmer.setVillage(farmerDto.getVillage());
		farmer.setPincode(farmerDto.getPincode());
		farmer.setBirthDate(farmerDto.getBirthDate());
		farmer.setPhoneNumber(farmerDto.getPhoneNumber());
		farmer.setOtp(farmerDto.getOtp());
		farmer.setPassword(farmerDto.getPassword());
		farmer.setConfirmPassword(farmerDto.getConfirmPassword());
		farmer.setPassword(hashedPassword);
		farmer.setConfirmPassword(hashedPassword);
		

		try {
			// Save the farmer in the database (you should handle exceptions)
			farmerService.registerFarmer(farmer);
			return ResponseEntity.ok(Map.of("success", "true", "message", "Farmer registered successfully"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Map.of("success", "false", "message", "Registration failed: " + e.getMessage()));
		}
	}

	private String hashPassword(String password) {
		// Implement your password hashing logic here (e.g., using passwordEncoder)
		return passwordEncoder.encode(password);
	}

	private boolean verifyOTP(String phoneNumber, String otp) {
		return true;
	}
	
	@GetMapping("/{id}") //working do not change 
    public ResponseEntity<FarmerDto> getFarmerProfile(@PathVariable Long id) {
        try {
            FarmerDto farmerDto = farmerService.getFarmerById(id);

            if (farmerDto != null) {
                return ResponseEntity.ok(farmerDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

	@GetMapping("/profile/{username}")//not working
    public ResponseEntity<?> getFarmerProfile(@PathVariable String username) {
        try {
            // Debugging: Log the search
            System.out.println("Searching for farmer with username: " + username);

            // Find the farmer profile by username (you should handle exceptions)
            Farmer farmer = farmerService.findByUsername(username);

            if (farmer != null) {
                // Debugging: Log the found farmer's name
                System.out.println("Found farmer: " + farmer.getName());

                // Create a DTO or map the Farmer entity to a response format
                UserDTO userProfile = new UserDTO();
                userProfile.setUsername(farmer.getName());
                userProfile.setPhoneNumber(farmer.getPhoneNumber());

                return ResponseEntity.ok(userProfile);
            } else {
                // Debugging: Log if the farmer is not found
                System.out.println("Farmer not found for username: " + username);

                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "User profile not found"));
            }
        } catch (Exception e) {
            // Debugging: Log any exceptions
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An error occurred while fetching the user profile"));
        }
    }

	@GetMapping("/all") //working do not change 
	public List<Farmer> getAllFarmers() {
		// Call the farmerService to retrieve all farmers
		return farmerService.getAllFarmers();
	}

	@PostMapping("/add") //working do not change 
	public ResponseEntity<String> addFarmer(@RequestBody FarmerDto farmerDto) {
		// Create a Farmer entity from the DTO
		Farmer farmer = new Farmer();
		farmer.setName(farmerDto.getName());
		farmer.setSurname(farmerDto.getSurname());
		farmer.setCountry(farmerDto.getCountry());
		farmer.setState(farmerDto.getState());
		farmer.setDistrict(farmerDto.getDistrict());
		farmer.setSubdistrict(farmerDto.getSubDistrict());
		farmer.setVillage(farmerDto.getVillage());
		farmer.setPincode(farmerDto.getPincode());
		farmer.setBirthDate(farmerDto.getBirthDate());
		farmer.setPhoneNumber(farmerDto.getPhoneNumber());
		farmer.setOtp(farmerDto.getOtp());
		farmer.setPassword(farmerDto.getPassword());
		farmer.setConfirmPassword(farmerDto.getConfirmPassword());
	

		Farmer savedFarmer = farmerService.saveFarmer(farmerDto);
		if (savedFarmer != null) {
			return ResponseEntity.ok("Farmer added successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add farmer");
		}
	}

	@PutMapping("/update/{id}") //working do not change 
	public ResponseEntity<Farmer> updateFarmer(@PathVariable Long id, @RequestBody FarmerDto updatedFarmerDto) {
		// Get the existing farmer from the service
		FarmerDto existingFarmer = farmerService.getFarmerById(id);
		if (existingFarmer == null) {
			return ResponseEntity.notFound().build();
		}

		// Update the existing farmer entity with the new data
		existingFarmer.setName(updatedFarmerDto.getName());
		existingFarmer.setSurname(updatedFarmerDto.getSurname());
		existingFarmer.setPhoneNumber(updatedFarmerDto.getPhoneNumber());
		existingFarmer.setCountry(updatedFarmerDto.getCountry());
		existingFarmer.setState(updatedFarmerDto.getState());
		existingFarmer.setDistrict(updatedFarmerDto.getDistrict());
		existingFarmer.setSubDistrict(updatedFarmerDto.getSubDistrict());
		existingFarmer.setVillage(updatedFarmerDto.getVillage());
		existingFarmer.setPincode(updatedFarmerDto.getPincode());

		// Save the updated farmer in the database
		Farmer updatedFarmer = farmerService.updateFarmer(id, existingFarmer);
		return ResponseEntity.ok(updatedFarmer);
	}

	@DeleteMapping("/delete/{id}") //working do not change 
	public ResponseEntity<String> deleteFarmer(@PathVariable Long id) {
		farmerService.deleteFarmer(id);
		return ResponseEntity.ok("Farmer deleted successfully");
	}
}
