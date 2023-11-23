package com.agri.agriculture.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agriculture.dto.WorkRequestDTO;
import com.agri.agriculture.entity.WorkRequest;
import com.agri.agriculture.service.WorkRequestService;

@RestController
@RequestMapping("/api/workrequests")
@CrossOrigin(origins = "https://agri-connect-qj8x.onrender.com") // Add your frontend URL here
public class WorkRequestController {

    private final WorkRequestService workRequestService;

    @Autowired
    public WorkRequestController(WorkRequestService workRequestService) {
        this.workRequestService = workRequestService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitWorkRequest(@RequestBody WorkRequestDTO workRequestDTO) {
        try {
            workRequestService.saveWorkRequest(workRequestDTO);
            return ResponseEntity.ok("Work request submitted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error submitting work request");
        }
    }

    @GetMapping("/all")
    public List<WorkRequest> getAllWorkRequst() {
        return workRequestService.getAllWorkRequst();
    }
}
