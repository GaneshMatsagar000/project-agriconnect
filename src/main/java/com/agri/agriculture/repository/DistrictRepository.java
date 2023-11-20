package com.agri.agriculture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.agriculture.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
}
