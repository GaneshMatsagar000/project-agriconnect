package com.agri.agriculture.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agriculture.dto.DistrictDTO;
import com.agri.agriculture.service.DistrictService;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @GetMapping
    public ResponseEntity<List<DistrictDTO>> getAllDistricts() {
        List<DistrictDTO> districts = districtService.getAllDistricts();
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistrictDTO> getDistrictById(@PathVariable Long id) {
        DistrictDTO district = districtService.getDistrictById(id);
        if (district != null) {
            return new ResponseEntity<>(district, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DistrictDTO> createDistrict(@RequestBody DistrictDTO districtDTO) {
        DistrictDTO createdDistrict = districtService.createDistrict(districtDTO);
        return new ResponseEntity<>(createdDistrict, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DistrictDTO> updateDistrict(@PathVariable Long id, @RequestBody DistrictDTO districtDTO) {
        DistrictDTO updatedDistrict = districtService.updateDistrict(id, districtDTO);
        if (updatedDistrict != null) {
            return new ResponseEntity<>(updatedDistrict, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Long id) {
        boolean deleted = districtService.deleteDistrict(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
