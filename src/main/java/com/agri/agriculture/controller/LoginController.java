package com.agri.agriculture.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/index.html")
    public String login() {
        return "index"; // Return "index" as the view name
    }
}

