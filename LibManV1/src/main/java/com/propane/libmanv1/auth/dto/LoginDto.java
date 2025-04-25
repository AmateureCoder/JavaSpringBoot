package com.propane.libmanv1.auth.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginDto {
    @Email
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
