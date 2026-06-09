package com.nha.nha_smos.Mapper;


import com.nha.nha_smos.DTO.CategoryRequest;
import com.nha.nha_smos.DTO.CategoryResponse;
import com.nha.nha_smos.Model.CategoryModel;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryModel toEntity(CategoryRequest dto) {
        return CategoryModel.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .status(Boolean.valueOf(dto.getStatus()))
                .description(dto.getDescription())
                .build();
    }

    public CategoryResponse  toResponse(CategoryModel entity) {
        return CategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedAt(entity.getUpdatedAt())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }
}
