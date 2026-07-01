package com.nha.nha_smos.Service;

import com.nha.nha_smos.DTO.ProductRequest;
import com.nha.nha_smos.DTO.ProductResponse;
import com.nha.nha_smos.Exception.ResourceNotFoundException;
import com.nha.nha_smos.Mapper.ProductMapper;
import com.nha.nha_smos.Model.BrandModel;
import com.nha.nha_smos.Model.CategoryModel;
import com.nha.nha_smos.Model.ProductModel;
import com.nha.nha_smos.Repository.BrandRepository;
import com.nha.nha_smos.Repository.CategoryRepository;
import com.nha.nha_smos.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;

    public ProductResponse saveProduct(ProductRequest req) {

        CategoryModel category = this.categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not Found!"));
        BrandModel brand = this.brandRepository.findById(Math.toIntExact(req.getBrandId()))
                .orElseThrow(() -> new ResourceNotFoundException("Brand Not Found!"));
        ProductModel  product =  this.productMapper.toEntity(req, category,brand);
        this.productRepository.save(product);
        return productMapper.toResponse(product);
    }

}
