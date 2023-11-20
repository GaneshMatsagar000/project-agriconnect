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

import com.agri.agriculture.dto.VillageDTO;
import com.agri.agriculture.service.VillageService;

@RestController
@RequestMapping("/api/villages")
public class VillageController {

    @Autowired
    private VillageService villageService;

    @GetMapping
    public ResponseEntity<List<VillageDTO>> getAllVillages() {
        List<VillageDTO> villages = villageService.getAllVillages();
        return new ResponseEntity<>(villages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VillageDTO> getVillageById(@PathVariable Long id) {
        VillageDTO village = villageService.getVillageById(id);
        if (village != null) {
            return new ResponseEntity<>(village, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<VillageDTO> createVillage(@RequestBody VillageDTO villageDTO) {
        VillageDTO createdVillage = villageService.createVillage(villageDTO);
        return new ResponseEntity<>(createdVillage, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VillageDTO> updateVillage(@PathVariable Long id, @RequestBody VillageDTO villageDTO) {
        VillageDTO updatedVillage = villageService.updateVillage(id, villageDTO);
        if (updatedVillage != null) {
            return new ResponseEntity<>(updatedVillage, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVillage(@PathVariable Long id) {
        boolean deleted = villageService.deleteVillage(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
