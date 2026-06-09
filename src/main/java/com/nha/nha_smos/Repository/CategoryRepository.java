package com.nha.nha_smos.Repository;

import com.nha.nha_smos.Model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
    boolean existsByCode(String code);
}
