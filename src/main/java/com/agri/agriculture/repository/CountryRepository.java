package com.agri.agriculture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.agriculture.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
