package com.agri.agriculture.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class WorkRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String farmerName;
	private String phoneNumber;
	private String farmAddress;
	private String workDetails;
	private String otherWorkDetails;
	private String fieldArea;
	private String otherFieldArea;
	private Date TractorNeed;
	private String paymentMode;
	private double latitude; // To store the latitude of the location
	private double longitude; // To store the longitude of the location

	
}
