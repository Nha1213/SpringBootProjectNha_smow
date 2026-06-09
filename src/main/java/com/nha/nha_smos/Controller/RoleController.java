package com.nha.nha_smos.Controller;


import com.nha.nha_smos.DTO.RoleRequest;
import com.nha.nha_smos.DTO.RoleResponse;
import com.nha.nha_smos.Model.RoleModel;
import com.nha.nha_smos.Service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.spel.ast.OpGE;
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
    public List<RoleResponse> GetAllRoles(){
        return roleService.getList();
    }

    @GetMapping("/search")
    public ResponseEntity<RoleResponse> search(@RequestParam("id") int id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody RoleRequest roleRequest){
        Map<String,Object> res = new HashMap<>();
        res.put("message", "Role Created");
        res.put("Role",roleService.create(roleRequest));
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> updateRole(@PathVariable Integer id, @RequestBody RoleRequest roleRequest){
        return ResponseEntity.ok(roleService.update(id, roleRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id){
        roleService.delete(id);
        Map<String, Object>  res =  new HashMap<>();
        res.put("message", "Role deleted successfully");
        return ResponseEntity.ok(res);

    }


}
