package com.nha.nha_smos.Service;

import com.nha.nha_smos.DTO.CategoryRequest;
import com.nha.nha_smos.DTO.CategoryResponse;
import com.nha.nha_smos.Mapper.CategoryMapper;
import com.nha.nha_smos.Model.CategoryModel;
import com.nha.nha_smos.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    // Display data category
    public List<CategoryResponse> gitList(){

        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }


    // Insert into Data Category
    public CategoryResponse save(CategoryRequest dto) {
        CategoryModel category = categoryMapper.toEntity(dto);
        CategoryModel savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }
}
