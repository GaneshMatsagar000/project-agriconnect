package com.agri.agriculture.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agriculture.dto.FarmerDto;
import com.agri.agriculture.entity.Farmer;
import com.agri.agriculture.service.FarmerService;

@RestController
@RequestMapping("/api")
public class LocationController {

    @GetMapping("/location")
    public ResponseEntity<Map<String, Double>> getCustomerLocation() {
        Map<String, Double> location = new HashMap<>();
        // Replace with your logic to retrieve the customer's location
        location.put("latitude", 37.7749); // Example latitude
        location.put("longitude", -122.4194); // Example longitude
        return ResponseEntity.ok(location);
    }
    
   

}
