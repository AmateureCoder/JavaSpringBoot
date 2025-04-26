package com.propane.libmanv1.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.propane.libmanv1.identity.model.User;
import com.propane.libmanv1.identity.service.imp.UserServiceImpl;

@Controller
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user/user-dashboard";
    }
    @GetMapping("/user/profile")
    public String userProfile(Model model) {
        User user = userService.getCurrentUser(); // Implement this method to get the current user
        model.addAttribute("user", user); // Add user details to the model

         return "user/user-profile"; // Ensure the user-profile.html exists in templates/user/
    }

    

    // Add more mappings for user-specific functionalities if needed
}