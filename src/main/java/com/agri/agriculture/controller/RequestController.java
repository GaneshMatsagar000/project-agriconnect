package com.agri.agriculture.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {
	

    @GetMapping("/workRequest.html")
    public String showWorkRequestPage() {
        return "workRequest"; // Assuming your HTML file is named "workRequest.html"
    }
    
    @GetMapping("/acceptFarmerRequest.html")
    public String showAcceptFarmerRequestPage() {
        return "acceptFarmerRequest"; // Assuming your HTML file is named "workRequest.html"
    }
    
    
}
