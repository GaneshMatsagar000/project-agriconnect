package com.agri.agriculture.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class TractorOwner implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "exception_seq_generator")
	@SequenceGenerator(name = "exception_seq_generator", sequenceName = "exception_seq", allocationSize = 1)
    private Long id;
    private String driverName;
    private String mobileNumber;
    private String password;
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
    // Add more fields and getters/setters as needed

    // Getter and setter methods for additional fields
}
