package com.nha.nha_smos.DTO.User;

import com.nha.nha_smos.Model.RoleModel;
import com.nha.nha_smos.Model.UserProfileModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private int id;
    private String email;
    private String password;
    private String phone;
    private LocalDate createdAt;

    private UserProfileModel profile;
//    private Set<RoleModel> roles;
    List<String>  roles;
    List<String> permissions;
    String accessToken;
    String refreshToken;
}

