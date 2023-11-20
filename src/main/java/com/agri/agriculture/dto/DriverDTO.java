package com.agri.agriculture.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class DriverDTO implements Serializable {
	private static final long serialVersionUID = -4871905314450545685L;
	private Long id;


    private String fullName;
    
    private String phoneNumber;

    private String otp;
   
    private String birthDate;
    
    private String createPassword;
   
    private String confirmPassword;
   
    private String tractorNumber;
    
    private String country;

    private String state;

    private String district;

    private String subdistrict;

    private String village;

    private long pincode;
    
}