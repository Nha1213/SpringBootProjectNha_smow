package com.nha.nha_smos.Service;

import com.nha.nha_smos.DTO.CategoryRequest;
import com.nha.nha_smos.Model.CategoryModel;
import com.nha.nha_smos.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryModel save(CategoryRequest dto) {
        CategoryModel  category = new CategoryModel();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setCode(dto.getCode());
        category.setStatus(dto.getStatus());
        return this.categoryRepository.save(category);
    }
}
