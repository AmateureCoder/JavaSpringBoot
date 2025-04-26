package com.propane.libmanv1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user/user-dashboard";
    }

    // Add more mappings for user-specific functionalities if needed
}