package com.nha.nha_smos.Repository;

import com.nha.nha_smos.Model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
}
