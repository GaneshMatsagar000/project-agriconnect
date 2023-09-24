package com.agri.agriculture.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class TractorOwnerDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String driverName;
    private String mobileNumber;
    private String otp;
    private String password;
    private String confirmPassword;
    private String tractorModel;
    private String tractorNumber;
    private String country;
    private String state;
    private String district;
    private String taluka;
    private String village;
    private String pinCode;
    private Double latitude;
    private Double longitude;

    // Constructors, getters, and setters
}
