package com.nha.nha_smos.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@Entity
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double price;
    private Boolean status;
    private String description;

//    @Column(nullable = false)
    private LocalDateTime createdAt;
    private String createdBy;

//    @Column(nullable = false)
    private LocalDateTime updatedAt;
    private String updatedBy;

    //relationship
    @ManyToOne(fetch = FetchType.LAZY)// is not get category default
    @JoinColumn(name = "categoryId") // create  in table product.cate_id(fk) -> reference to table category.id
    private CategoryModel category;  // target to Class CategoryModel

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brandId")
    private BrandModel brand;

    @PrePersist
    public void  onCreate(){
        this.createdAt = LocalDateTime.now();
        this.createdBy = "nha";
        if(this.status == null){
            this.status = true;
        }
    }

    @PreUpdate
    public void  onUpdate(){
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = "nha";
    }
}
