package com.agri.agriculture.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agri.agriculture.dto.FarmerDto;
import com.agri.agriculture.entity.Farmer;

@Repository
public interface FarmerRepository extends CrudRepository<Farmer, Long> {

	Farmer save(FarmerDto farmerDto);
    
    
}
