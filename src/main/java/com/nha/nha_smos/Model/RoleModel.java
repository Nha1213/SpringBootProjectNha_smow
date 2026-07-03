package com.nha.nha_smos.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "userId")
//    private UserModel user;

    private String description;

    private String test;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
//    when we want to get role have permission it will get.permissions
    private Set<PermissionModel> permissions = new HashSet<>();
}