package com.nha.nha_smos.Mapper;

import com.nha.nha_smos.DTO.ProductRequest;
import com.nha.nha_smos.DTO.ProductResponse;
import com.nha.nha_smos.Model.BrandModel;
import com.nha.nha_smos.Model.CategoryModel;
import com.nha.nha_smos.Model.ProductModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductModel toEntity(ProductRequest dto, CategoryModel category, BrandModel brand){
//        ProductModel product = new ProductModel();
//        product.setProductName(dto.getProductName());
//        return product;
        return ProductModel.builder()
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .category(category)
                .brand(brand)
                .build();
    }

    public ProductResponse toResponse(ProductModel dto){
        return ProductResponse.builder()
                .id(dto.getId())
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .categoryId(dto.getCategory().getId())
                .categoryName(dto.getCategory().getName())
                .brandId(dto.getBrand().getId())
                .brandName(dto.getBrand().getName())
                .build();
    }
}
