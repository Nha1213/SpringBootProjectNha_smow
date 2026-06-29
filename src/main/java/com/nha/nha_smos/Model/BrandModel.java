package com.nha.nha_smos.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="brands", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    private String description;

    @Column(nullable = false)
    private Boolean status = true;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String createdBy;

    private  LocalDateTime updatedAt;
    private String updatedBy;


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        if(this.status == null) {
            this.status = true;
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
