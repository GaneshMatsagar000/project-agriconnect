package com.agri.agriculture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "farmers")
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String Name;

    @Column(name = "surname")
    private String Surname;
    
    
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
    private String pincode;
    
    @Column(name = "birthDate")
    private String birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "otp")
    private String otp;

    @Column(name = "password")
    private String password;
    
    @Column(name = "ConfirmPassword")
    private String ConfirmPassword;

    @OneToOne
    @JoinColumn(name = "user_id") // Assuming there's a "user_id" column in the database
    private User user;
}