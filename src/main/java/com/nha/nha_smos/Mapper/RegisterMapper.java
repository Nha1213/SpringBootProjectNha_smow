package com.nha.nha_smos.Mapper;

import com.nha.nha_smos.DTO.User.RegisterRequest;
import com.nha.nha_smos.DTO.User.RegisterResponse;
import com.nha.nha_smos.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {

    public UserModel toEntity(RegisterRequest dto) {
        return UserModel.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
    }

    public RegisterResponse toResponse(UserModel entity) {
        return RegisterResponse.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .phone(entity.getPhone())
                .profile(entity.getProfile())
                .roles(entity.getRoles())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
