package com.nha.nha_smos.Model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String phone;

    private boolean isActive = true;

    @CreationTimestamp
    private LocalDate createdAt;

    private LocalDate updatedAt;

    // auto crate table uer_roles, and user_id reverent to
    // roleModel, role_id referent to user_roles
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn( name = "role_id")
    )
    private Set<RoleModel> roles = new HashSet<>();

    //one to one with profile
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserProfileModel profile;

}
