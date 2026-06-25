package com.nha.nha_smos.Repository;

import com.nha.nha_smos.Model.CategoryModel;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
    boolean existsByCode(String code);

    List<CategoryModel> findAll(Specification<CategoryModel> spec);
}
