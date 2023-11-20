package com.agri.agriculture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.agriculture.entity.WorkRequest;

@Repository
public interface WorkRequestRepository extends JpaRepository<WorkRequest, Long> {
    // You can add custom query methods here if needed
}
