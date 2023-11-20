package com.agri.agriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/register")
	public String showRegisterPage() {
		return "register"; // This maps to the register.html view
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login"; // This maps to the login.html view
	}
	
	@GetMapping("/driverregister")
	public String showDriverRegister() {
		return "driverregister";
	}
}
