package com.agri.agriculture.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.agri.agriculture.dto.FarmerDto;
import com.agri.agriculture.entity.Farmer;

@Repository
public interface FarmerRepository extends CrudRepository<Farmer, Long> {

	Farmer save(FarmerDto farmerDto);

	Optional<Farmer> findByPhoneNumber(String username);
    
    
}
