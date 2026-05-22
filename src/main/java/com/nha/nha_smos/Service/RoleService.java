package com.nha.nha_smos.Service;

import com.nha.nha_smos.Entity.Role;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    private final Map<Integer, Role> roleStore = new HashMap<>();
    private int idCounter = 0;

    // Get all roles
    public List<Role> list() {
        return new ArrayList<>(roleStore.values());
    }

    // Get one role
    public Role listOne(Integer id) {
        return roleStore.get(id);
    }

    // Create role
    public Role create(Role role) {
        idCounter++;
        role.setId(idCounter);

        roleStore.put(idCounter, role);

        return role;
    }

    // Update role
    public Role update(Integer id, Role role) {

        if(roleStore.containsKey(id)) {
            role.setId(id);
            roleStore.put(id, role);

            return role;
        }

        return null;
    }

    // Delete role
    public String delete(Integer id) {

        roleStore.remove(id);

        return "Deleted Successfully";
    }
}