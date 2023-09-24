package com.agri.agriculture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agri.agriculture.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
}
