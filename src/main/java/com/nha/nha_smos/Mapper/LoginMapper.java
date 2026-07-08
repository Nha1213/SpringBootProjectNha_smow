package com.nha.nha_smos.Mapper;

import com.nha.nha_smos.DTO.User.LoginResponse;
import com.nha.nha_smos.DTO.User.RegisterRequest;
import com.nha.nha_smos.DTO.User.RegisterResponse;
import com.nha.nha_smos.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    public UserModel toEntity(RegisterRequest dto) {
        return UserModel.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
    }

    public LoginResponse toResponse(UserModel entity) {
        return LoginResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phone(entity.getPhone())
                .profile(entity.getProfile())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}