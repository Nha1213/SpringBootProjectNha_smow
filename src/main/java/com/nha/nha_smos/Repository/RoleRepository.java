package com.nha.nha_smos.Repository;

import com.nha.nha_smos.Model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Integer> {
    boolean existsByName(String name);
    Optional<RoleModel> findByName(String roleName);
}