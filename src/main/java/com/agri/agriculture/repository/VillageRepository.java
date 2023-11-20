package com.agri.agriculture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.agriculture.entity.Village;

@Repository
public interface VillageRepository extends JpaRepository<Village, Long> {
}
