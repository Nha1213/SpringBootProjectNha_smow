package com.nha.nha_smos.Model;

import jakarta.persistence.*;
import lombok.*;

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
}