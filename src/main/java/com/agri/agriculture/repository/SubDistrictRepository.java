package com.agri.agriculture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.agriculture.entity.SubDistrict;

@Repository
public interface SubDistrictRepository extends JpaRepository<SubDistrict, Long> {
}
