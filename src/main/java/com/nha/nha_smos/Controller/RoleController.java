package com.nha.nha_smos.Controller;

import com.nha.nha_smos.Entity.Role;
import com.nha.nha_smos.Service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    // Dependency Injection
    private final RoleService roleService;

    // Constructor Injection
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    // Get all roles
    @GetMapping
    public List<Role> list(){
        return this.roleService.list();
    }

    // Get one role
    @GetMapping("/{id}")
    public Role listOne(@PathVariable Integer id){
        return this.roleService.listOne(id);
    }

    // Create role
    @PostMapping
    public Role create(@RequestBody Role role){
        return this.roleService.create(role);
    }

    // Update role
    @PutMapping("/{id}")
    public Role update(
            @PathVariable Integer id,
            @RequestBody Role role){

        return this.roleService.update(id, role);
    }

    // Delete role
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        return this.roleService.delete(id);
    }
}