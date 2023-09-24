package com.agri.agriculture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agri.agriculture.entity.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    // Define custom queries if needed
}