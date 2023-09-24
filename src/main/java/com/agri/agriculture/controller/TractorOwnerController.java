package com.agri.agriculture.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agri.agriculture.dto.TractorOwnerDto;
import com.agri.agriculture.entity.TractorOwner;
import com.agri.agriculture.service.TractorOwnerService;


@Controller
@RequestMapping( "/api/tractorowners")
public class TractorOwnerController {

	@Autowired
	private TractorOwnerService tractorOwnerService;

	

	@PostMapping("/registerTractorOwner")
	public ResponseEntity<?> registerTractorOwner(@RequestBody TractorOwnerDto tractorOwnerDto) {
	    try {
	        TractorOwner registeredOwner = tractorOwnerService.registerTractorOwner(tractorOwnerDto);
	        return ResponseEntity.ok(registeredOwner);
	    } catch (Exception e) {
	        // Handle exceptions and return an error response in JSON format
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("error", e.getMessage()); // Customize the error message as needed
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
	}

}

