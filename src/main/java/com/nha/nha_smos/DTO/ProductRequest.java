package com.nha.nha_smos.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotEmpty(message = "Name is required!")
    private String productName;
    @NotNull(message = "quantity is required!")
    @Min(value = 0, message = "Quantity must better than 0")
    private Integer quantity;
    @NotNull(message = "price is required!")
    @Min(value = 0, message = "price must better than 0")
    private Double price;
    @NotNull(message = "status is required!")
    private Boolean status;
    private String description;
    @NotNull(message = "CategoryId is required!")
    private Integer categoryId;
    @NotNull(message = "BrandId is required!")
    private Long brandId;
}
