package com.nha.nha_smos.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String productName;
    private Integer quantity;
    private Double price;
    private Boolean status;
    private String description;
    private Integer categoryId;
    private Long brandId;
    private String brandName;
    private String categoryName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
