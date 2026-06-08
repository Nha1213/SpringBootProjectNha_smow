package com.nha.nha_smos.Controller;


import com.nha.nha_smos.Model.RoleModel;
import com.nha.nha_smos.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    //constructor
//    public RoleController(RoleService roleService) {
//        this.roleService = roleService;
//    }

    @GetMapping
    ResponseEntity<Map> findAll() {
        Map res = new HashMap();
        res.put("message", "Role List");
        res.put("list", roleService.getList());
        return ResponseEntity.ok(res);
    }

    @GetMapping("/search")
    ResponseEntity<Map> search(@RequestParam("id") int id) {
        Map res = new HashMap();
        res.put("message", "Role List");
        res.put("list", roleService.getRoleById(id));
        return ResponseEntity.ok(res);
    }

    @PostMapping
    ResponseEntity<RoleModel> createRole(@RequestBody RoleModel roleModel){
        return ResponseEntity.status(201).body(roleService.create(roleModel));
    }
//    ResponseEntity<Map> create(@RequestBody RoleModel roleModel){
//        Map res = new HashMap();
//        res.put("message", "Create Role");
//        res.put("list", roleService.saveRole(roleModel));
//        return ResponseEntity.ok(res);
//        return ResponseEntity.status(200).body(res);
//    }

    @PutMapping("/{id}")
    ResponseEntity<RoleModel> updateRole(@PathVariable Integer id, @RequestBody RoleModel roleModel){
        return ResponseEntity.status(201).body(roleService.update(id, roleModel));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteRole(@PathVariable Integer id){
        roleService.delete(id);
        return ResponseEntity.ok("Role Deleted");
    }


}


//
//import com.nha.nha_smos.Entity.Role;
//import com.nha.nha_smos.Service.RoleService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//@RestController
//@RequestMapping("/api/role")
//public class RoleController {
//
//    // Dependency Injection
//    private final RoleService roleService;
//
//    // Constructor Injection
//    public RoleController(RoleService roleService){
//        this.roleService = roleService;
//    }
//
//    // Get all roles
//    @GetMapping
//    public List<Role> list(){
//        return this.roleService.list();
//    }
//
//    // Get one role
//    @GetMapping("/{id}")
//    public Role listOne(@PathVariable Integer id){
//        return this.roleService.listOne(id);
//    }
//
//    // Create role
//    @PostMapping
//    public Role create(@RequestBody Role role){
//        return this.roleService.create(role);
//    }
//
//    // Update role
//    @PutMapping("/{id}")
//    public Role update(
//            @PathVariable Integer id,
//            @RequestBody Role role){
//
//        return this.roleService.update(id, role);
//    }
//
//    // Delete role
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable Integer id){
//        return this.roleService.delete(id);
//    }
//}