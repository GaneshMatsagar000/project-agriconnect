package com.agri.agriculture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.agriculture.entity.TractorOwner;


@Repository
public interface TractorOwnerRepository extends JpaRepository<TractorOwner, Long> {
   
}
