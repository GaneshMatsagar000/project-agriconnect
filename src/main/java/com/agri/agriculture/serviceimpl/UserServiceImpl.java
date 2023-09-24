package com.agri.agriculture.serviceimpl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agri.agriculture.ExceptionClass.InvalidOTPException;
import com.agri.agriculture.ExceptionClass.UserAlreadyExistsException;
import com.agri.agriculture.ExceptionClass.UserNotFoundException;
import com.agri.agriculture.dto.OTPVerificationDTO;
import com.agri.agriculture.dto.UserDTO;
import com.agri.agriculture.dto.UserRegistrationDTO;
import com.agri.agriculture.entity.User;
import com.agri.agriculture.repository.UserRepository;
import com.agri.agriculture.service.UserService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class UserServiceImpl implements UserService {
    private static final String ACCOUNT_SID = "AC5c002106437e3daaa77edc0d37a414ac";
    private static final String AUTH_TOKEN = "b64ce793471306cc2eea249c00628388";
    private static final String TWILIO_PHONE_NUMBER = "+12569078184";

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO registerUser(UserRegistrationDTO registrationDTO) {
        // Check if the user already exists
        if (userRepository.existsByPhoneNumber(registrationDTO.getPhoneNumber())) {
            throw new UserAlreadyExistsException("User with this phone number already exists.");
        }

        // Generate OTP (a 6-digit random number)
        String otp = generateOTP();

        // Save the user to the database as unverified
        User newUser = new User();
        newUser.setPhoneNumber(registrationDTO.getPhoneNumber());
        newUser.setUserType(registrationDTO.getUserType());
        newUser.setVerified(false);
        newUser.setOtp(otp); // Save OTP for verification
        userRepository.save(newUser);

        // Send OTP to the provided phone number
        sendOTP(registrationDTO.getPhoneNumber(), otp);

        // Create a UserDTO to return user information
        UserDTO userDTO = new UserDTO();
        userDTO.setId(newUser.getId());
        userDTO.setPhoneNumber(newUser.getPhoneNumber());
        userDTO.setUserType(newUser.getUserType());
        userDTO.setVerified(newUser.isVerified());

        return userDTO;
    }

    @Override
    public void sendOTP(String phoneNumber) {
        // Generate a random OTP (a 6-digit number)
        String otp = generateOTP();

        // Save the OTP to the user's record in the database
        Optional<User> userOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found.");
        }

        User user = userOptional.get();
        user.setOtp(otp);
        userRepository.save(user);

        // Send the OTP via SMS using Twilio
        sendOTP(phoneNumber, otp);
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
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generates a random 6-digit number
        return String.valueOf(otp);
    }

    public void sendOTP(String phoneNumber, String otp) {
        // Initialize the Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Send the OTP via SMS
        Message message = Message
                .creator(new PhoneNumber(phoneNumber), new PhoneNumber(TWILIO_PHONE_NUMBER), "Your OTP is: " + otp)
                .create();

        // Handle any errors or log the result as needed
        if (message.getStatus() != Message.Status.SENT) {
            throw new RuntimeException("Failed to send OTP via Twilio.");
        }
    }


}
