package com.agri.agriculture.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class FarmerDto implements Serializable {
    private static final long serialVersionUID = -4871905314450545685L;
    private Long id;    
    @NotBlank(message = "Name is required")
    private String Name;   
    @NotBlank(message = "surname is required")
    private String Surname; 
    private String country;
    private String state;
    private String district;
    private String subDistrict;
    private String village;
    private String pincode;    
    private String birthDate;
    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phoneNumber;
    private String otp;
    private String password;
    private String confirmPassword;
    
    private List<TractorOwnerDto> tractorsOwned;

    // Constructors, getters, and setters


    public List<TractorOwnerDto> getTractorsOwned() {
        return tractorsOwned;
    }

    public void setTractorsOwned(List<TractorOwnerDto> tractorsOwned) {
        this.tractorsOwned = tractorsOwned;
    }
}
