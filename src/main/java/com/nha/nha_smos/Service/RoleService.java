package com.nha.nha_smos.Service;



import com.nha.nha_smos.DTO.RoleRequest;
import com.nha.nha_smos.DTO.RoleResponse;
import com.nha.nha_smos.Mapper.RoleMapper;
import com.nha.nha_smos.Model.RoleModel;
import com.nha.nha_smos.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // It had no  constructor If have constructor not RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    //constructor
//    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
//        this.roleRepository = roleRepository;
//        this.roleMapper = roleMapper;
//    }

    public List<RoleResponse> getList() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponse)
                .toList();
    }

    public  RoleResponse getRoleById(int id){

        RoleModel role = roleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Role Not Found"));
        return roleMapper.toResponse(role);

    }


    public RoleResponse create(RoleRequest roleRequest){
        if(roleRepository.existsByName(roleRequest.getName())){
            throw new RuntimeException("Role Already Exists");
        }

        RoleModel role = roleMapper.toEntityTwo(roleRequest);
        RoleModel roleModel = roleRepository.save(role);
        return roleMapper.toResponse(roleModel);
    }

    public RoleResponse update(Integer id, RoleRequest roleRequest){
        RoleModel role=   roleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Role Not Found"));
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        role.setTest(roleRequest.getTest());

        RoleModel roleModel = roleRepository.save(role);

        return roleMapper.toResponse(roleModel);
    }

    public void delete(Integer id){
        if(roleRepository.existsById(id)){
            roleRepository.deleteById(id);
        }
    }


}



