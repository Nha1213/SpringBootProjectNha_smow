package com.nha.nha_smos.Mapper;

import com.nha.nha_smos.DTO.BrandRequest;
import com.nha.nha_smos.DTO.BrandResponse;
import com.nha.nha_smos.Model.BrandModel;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {
    public BrandModel toEntity(BrandRequest dto){
//        BrandModel brand = new BrandModel();
//        brand.setName(dto.getName());
//        brand.setDescription(dto.getDescription());
        return BrandModel.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .description(dto.getDescription())
                .status(dto.getStatus() == null || dto.getStatus())
                .createdBy(dto.getCreatedBy())
                .updatedBy(dto.getUpdatedBy())
                .build();
    }

    public BrandResponse toResponse(BrandModel entity){
        return BrandResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .createdAt(entity.getUpdatedAt())
                .createdBy(entity.getCreatedBy())
                .updatedAt(entity.getUpdatedAt())
                .updatedBy(entity.getUpdatedBy())
                .build();
    }

    public BrandRequest toRequest(BrandResponse entity){
        return BrandRequest.builder()
                .name(entity.getName())
                .code(entity.getCode())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .updatedBy(entity.getUpdatedBy())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
