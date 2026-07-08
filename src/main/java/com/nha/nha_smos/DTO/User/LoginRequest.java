package com.nha.nha_smos.DTO.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;

@Data
@Setter @Getter
public class LoginRequest {
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, max = 16, message = "Phone must be 8-16 characters")
    private String phone;

    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;

}
