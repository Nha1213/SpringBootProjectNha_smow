package com.nha.nha_smos.Service;



import com.nha.nha_smos.Model.RoleModel;
import com.nha.nha_smos.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    //constructor
//    public RoleService(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }

    public List<RoleModel> getList() {
        return roleRepository.findAll();
    }

    public  RoleModel getRoleById(int id){
        return roleRepository.findById(id).get();
    }


    public RoleModel create(RoleModel roleModel){
        return roleRepository.save(roleModel);
    }

    public RoleModel update(Integer id, RoleModel roleModel){
        RoleModel isExistRole = roleRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Role Not Found")
        );
        isExistRole.setName(roleModel.getName());
        isExistRole.setDescription(roleModel.getDescription());
        isExistRole.setTest(roleModel.getTest());
        return roleRepository.save(isExistRole);
    }

    public void delete(Integer id){
        RoleModel isExistRole = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role Not Found"));
        roleRepository.delete(isExistRole);
    }


}









//
//import com.nha.nha_smos.Entity.Role;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class RoleService {
//
//    private final Map<Integer, Role> roleStore = new HashMap<>();
//    private int idCounter = 0;
//
//    // Get all roles
//    public Map<String, Object> list() {
//        Map<String, Object> result = new HashMap<>();
//        result.put("roles", roleStore.values());
//        result.put("Message", "success");
//        result.put("count", 19);
//        return  result;
//    }
////    public List<Role> list() {
////        return new ArrayList<>(roleStore.values());
////    }
//
//    // Get one role
//    public Role listOne(Integer id) {
//        return roleStore.get(id);
//    }
//
//    // Create role
//    public Role create(Role role) {
//        idCounter++;
//        role.setId(idCounter);
//
//        roleStore.put(idCounter, role);
//
//        return role;
//    }
//
//    // Update role
//    public Role update(Integer id, Role role) {
//
//        //containsKey is find key
//        if(roleStore.containsKey(id)) {
//            role.setId(id);
//            roleStore.put(id, role);
//
//            return role;
//        }
//        else {
//         throw  new RuntimeException("Role Not Found");
//        }
//    }
//
//    // Delete role
//    public String delete(Integer id) {
//
//        roleStore.remove(id);
//
//        return "Deleted Successfully";
//    }
//