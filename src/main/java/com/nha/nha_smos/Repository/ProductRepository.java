package com.nha.nha_smos.Repository;

import com.nha.nha_smos.Model.ProductModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
    Page<ProductModel> findAll(Specification<ProductModel> spec, Pageable pageable);
}
