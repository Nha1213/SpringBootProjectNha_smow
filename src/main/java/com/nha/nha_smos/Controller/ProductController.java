package com.nha.nha_smos.Controller;

import com.nha.nha_smos.DTO.ProductRequest;
import com.nha.nha_smos.DTO.ProductResponse;
import com.nha.nha_smos.Model.ProductModel;
import com.nha.nha_smos.Service.ProductService;
import com.nha.nha_smos.Util.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> saveProduct(@Valid @RequestBody ProductRequest req){
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.create(productService.saveProduct(req)));
    }
}
