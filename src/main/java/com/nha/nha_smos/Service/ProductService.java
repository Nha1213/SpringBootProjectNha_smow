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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ProductResponse> list(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return productRepository.findAll(sort)
                .stream().map(productMapper::toResponse)
                .toList();
    }

    public  ProductResponse listById(int id){
        ProductModel  product =  this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));
        return productMapper.toResponse(product);
    }
}
