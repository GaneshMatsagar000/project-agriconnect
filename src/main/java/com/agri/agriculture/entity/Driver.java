package com.agri.agriculture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String fullName;
    
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "otp")
    private String otp;
    
    @Column(name = "birthDate")
    private String birthDate;
    
    @Column(name = "createdPassword")
    private String createPassword;
    
    @Column(name = "confirmedPassword")
    private String confirmPassword;
    
    
    @Column(nullable = false)
    private String tractorNumber;
    
    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "district")
    private String district;

    @Column(name = "subdistrict")
    private String subdistrict;

    @Column(name = "village")
    private String village;

    @Column(name = "pincode")
    private long pincode;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    

    // Constructors, getters, and setters
}
