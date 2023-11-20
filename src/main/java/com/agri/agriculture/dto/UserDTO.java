package com.agri.agriculture.dto;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class UserDTO  {
    private Long id;
    private String username;
    private String phoneNumber;
    // Other fields, getters, and setters
	public void setUserType(String userType) {
		// TODO Auto-generated method stub
		
	}
	public void setVerified(boolean verified) {
		// TODO Auto-generated method stub
		
	}
}
