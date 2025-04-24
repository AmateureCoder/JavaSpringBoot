package com.propane.libmanv1.auth.controller;
import com.propane.libmanv1.auth.dto.LoginDto;
import com.propane.libmanv1.auth.dto.RegistrationDto;
import com.propane.libmanv1.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @GetMapping("/register")
    public String showRegistration(Model model) {
        model.addAttribute("user", new RegistrationDto());
        return "register";
    }
    @PostMapping("/register")
    public String register(
            @ModelAttribute("user") @Valid RegistrationDto dto,
            BindingResult br
    ) {
        if (br.hasErrors()) return "register";
        authService.register(dto);
        return "redirect:/login?registered";
    }
    @GetMapping("/login")
    public String showLoginForm(
            @RequestParam(value="error", required=false) String error,
            @RequestParam(value="logout", required=false) String logout,
            @RequestParam(value="registered", required=false) String registered,
            @RequestParam(value="success", required=false) String success,
            Authentication auth,
            Model model
    ) {
        model.addAttribute("loginDto", new LoginDto());
        if (error != null)      model.addAttribute("loginError", true);
        if (logout != null)     model.addAttribute("loggedOut", true);
        if (registered != null) model.addAttribute("justRegistered", true);
        if (success != null && auth != null) {
            model.addAttribute("loginSuccess", auth.getName());
            model.addAttribute("adminSuccess",
                    auth.getAuthorities().stream()
                            .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()))
            );
        }
        return "login";
    }

}