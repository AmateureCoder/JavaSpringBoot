package dev.mahfuz.yayjobs.controller;

import dev.mahfuz.yayjobs.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new UserDto());
        return "register";
    }
    @GetMapping("/error")
    public String errorPage(){
        return "error";
    }
    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user")
            @Valid UserDto userDto,
            BindingResult result,
            Model model
    ) {
        if(result.hasErrors()){
            return "register";
        }
        System.out.println("User registered successfully: " + userDto);
        return "redirect:/login?registered";
    }
}
