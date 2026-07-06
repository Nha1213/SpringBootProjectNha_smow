package com.nha.nha_smos.DTO.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 8, max = 16, message = "Phone must be 8-16 characters")
    private String phone;

    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;

    //optional profile
    private String lastName;
    private String firstName;
    private String gender;
    private LocalDate dateOfBirth;
    private String address;
    private String image;

    //optional role
    private String roleName;
}