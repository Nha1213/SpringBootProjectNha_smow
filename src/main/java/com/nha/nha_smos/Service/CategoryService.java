package com.nha.nha_smos.Service;

import com.nha.nha_smos.DTO.CategoryRequest;
import com.nha.nha_smos.DTO.CategoryResponse;
import com.nha.nha_smos.Exception.ResourceNotFoundException;
import com.nha.nha_smos.Mapper.CategoryMapper;
import com.nha.nha_smos.Model.CategoryModel;
import com.nha.nha_smos.Repository.CategoryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

    public Map<String, Object> filter(Long id, String name, Boolean status){

        //Category entity
        Specification<CategoryModel> spec = Specification.unrestricted();

        if(id != null){
//            CriteriaBuilder(=, notNull  <, >, =>)
//            CriteriaQuery(join table or gp by)
//            Root is get field from CategoryModel
            spec = spec.and((Root<CategoryModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                        cb.equal(root.get("id"), id)
                    );
        }
        if(name != null && !name.isEmpty()){
            spec = spec.and((Root<CategoryModel> root, CriteriaQuery<?> equery, CriteriaBuilder cb)->
//                        cb.equal(root.get("name"), name)
//                        cb.like(root.get("name"), "%"+name+"%")
                        // like sql select from cate where name like "%$Name%"
                        cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%")
                    );
        }
        if(status != null){
            spec = spec.and((Root<CategoryModel> root, CriteriaQuery<?> query, CriteriaBuilder cb)->
                    cb.equal(root.get("status"), status)
            );
        }
        List<CategoryModel> category = this.categoryRepository.findAll(spec);
        Map<String, Object> response = new HashMap<>();
        response.put("data", category);
        return response;
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

    public CategoryResponse update(int id, CategoryRequest dto) {
        CategoryModel category = this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"+ id));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setStatus(dto.getStatus());
        category.setCode(dto.getCode());
        CategoryModel newCategory  = this.categoryRepository.save(category);
        return this.categoryMapper.toResponse(newCategory);
    }

    public void delete(int id){
        if(this.categoryRepository.existsById(id)){
//            this.categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Delete Category Not Found!"+ id));
             this.categoryRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Delete Category Not Found!"+ id);
        }

    }








}
