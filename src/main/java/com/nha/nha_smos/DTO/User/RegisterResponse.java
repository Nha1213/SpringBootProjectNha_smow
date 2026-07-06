package com.nha.nha_smos.DTO.User;

import com.nha.nha_smos.Model.RoleModel;
import com.nha.nha_smos.Model.UserProfileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private String email;
    private String password;
    private String phone;
    private LocalDate createdAt;

    private UserProfileModel profile;
    private Set<RoleModel> roles;
}
