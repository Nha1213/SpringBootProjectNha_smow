package com.nha.nha_smos.Mapper;


import com.nha.nha_smos.DTO.RoleRequest;
import com.nha.nha_smos.DTO.RoleResponse;
import com.nha.nha_smos.Model.RoleModel;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleModel toEntity(RoleRequest dto){
        RoleModel roleModel = new RoleModel();
        roleModel.setName(dto.getName());
        roleModel.setDescription(dto.getDescription());
        return  roleModel;
    }

    public RoleModel  toEntityTwo(RoleRequest dto){

        return  RoleModel.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .test(dto.getTest())
                .build();
    }

    public RoleResponse toResponse(RoleModel entity){
        return RoleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .test(entity.getTest())
                .build();
    }



}
