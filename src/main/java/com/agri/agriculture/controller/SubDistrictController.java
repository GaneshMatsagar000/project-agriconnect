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

import com.agri.agriculture.dto.SubDistrictDTO;
import com.agri.agriculture.service.SubDistrictService;

@RestController
@RequestMapping("/api/subdistricts")
public class SubDistrictController {

    @Autowired
    private SubDistrictService subDistrictService;

    @GetMapping
    public ResponseEntity<List<SubDistrictDTO>> getAllSubDistricts() {
        List<SubDistrictDTO> subDistricts = subDistrictService.getAllSubDistricts();
        return new ResponseEntity<>(subDistricts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubDistrictDTO> getSubDistrictById(@PathVariable Long id) {
        SubDistrictDTO subDistrict = subDistrictService.getSubDistrictById(id);
        if (subDistrict != null) {
            return new ResponseEntity<>(subDistrict, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SubDistrictDTO> createSubDistrict(@RequestBody SubDistrictDTO subDistrictDTO) {
        SubDistrictDTO createdSubDistrict = subDistrictService.createSubDistrict(subDistrictDTO);
        return new ResponseEntity<>(createdSubDistrict, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubDistrictDTO> updateSubDistrict(@PathVariable Long id, @RequestBody SubDistrictDTO subDistrictDTO) {
        SubDistrictDTO updatedSubDistrict = subDistrictService.updateSubDistrict(id, subDistrictDTO);
        if (updatedSubDistrict != null) {
            return new ResponseEntity<>(updatedSubDistrict, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubDistrict(@PathVariable Long id) {
        boolean deleted = subDistrictService.deleteSubDistrict(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
