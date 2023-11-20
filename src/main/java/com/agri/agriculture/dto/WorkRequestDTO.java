package com.agri.agriculture.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class WorkRequestDTO {

    private String farmerName;
    private String phoneNumber;
    private String farmAddress;
    private String workDetails;
    private String otherWorkDetails;
    private String fieldArea;
    private String otherFieldArea;
    private Date TractorNeed;
    private String paymentMode;
    private double latitude;
    private double longitude;

   
}
