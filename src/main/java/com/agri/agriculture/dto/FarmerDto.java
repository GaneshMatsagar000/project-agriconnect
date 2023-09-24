package com.agri.agriculture.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;


@Data
public class FarmerDto implements Serializable {
    private static final long serialVersionUID = -4871905314450545685L;
    private Long id;
    private String farmAddress;
    private String workDetails;
    private String paymentMode;
    private String otp;
    
    @NotBlank(message = "Full name is required")
    private String fullName; 
    
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phoneNumber;
    
    private String birthDate;
    private String password;
    private String confirmPassword;
    private String country;
    private String state;
    private String district;
    private String subdistrict;
    private String village;
    private String pincode;
    private List<TractorOwnerDto> tractorsOwned;

    // Constructors, getters, and setters


    public List<TractorOwnerDto> getTractorsOwned() {
        return tractorsOwned;
    }

    public void setTractorsOwned(List<TractorOwnerDto> tractorsOwned) {
        this.tractorsOwned = tractorsOwned;
    }
}
