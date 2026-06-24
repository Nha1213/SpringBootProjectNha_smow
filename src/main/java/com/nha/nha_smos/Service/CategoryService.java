package com.nha.nha_smos.Service;

import com.nha.nha_smos.DTO.CategoryRequest;
import com.nha.nha_smos.DTO.CategoryResponse;
import com.nha.nha_smos.Exception.ResourceNotFoundException;
import com.nha.nha_smos.Mapper.CategoryMapper;
import com.nha.nha_smos.Model.CategoryModel;
import com.nha.nha_smos.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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


    public CategoryResponse search(int id){
        CategoryModel category = categoryRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Category not found")
        );
        return categoryMapper.toResponse(category);
    }


    // Insert into Data Category
    public CategoryResponse save(CategoryRequest dto) {
        CategoryModel category = categoryMapper.toEntity(dto);
        CategoryModel savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

//    public CategoryResponse update(int id, CategoryRequest dto) {
//        CategoryModel cateory = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category file"+ id));
//
//    }






}
