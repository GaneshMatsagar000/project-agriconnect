package com.agri.agriculture.dto;

import lombok.Data;

@Data
public class OTPVerificationDTO {
    private String otp;
    private String phoneNumber;

    // Getter and setter methods for otp and phoneNumber
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
