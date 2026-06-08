package com.nha.nha_smos.Repository;

import com.nha.nha_smos.Model.BrandModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandModel, Integer> {
    boolean existsByCode(String code);
}
