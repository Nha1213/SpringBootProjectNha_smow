package com.nha.nha_smos.Service;

import com.nha.nha_smos.DTO.CategoryRequest;
import com.nha.nha_smos.DTO.CategoryResponse;
import com.nha.nha_smos.DTO.PageResponse;
import com.nha.nha_smos.Exception.ResourceNotFoundException;
import com.nha.nha_smos.Mapper.CategoryMapper;
import com.nha.nha_smos.Model.CategoryModel;
import com.nha.nha_smos.Repository.CategoryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    // Display data category
    public List<CategoryResponse> gitList(){

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryRepository.findAll(sort)
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    public PageResponse<CategoryResponse> filter(
            Long id, String name, Boolean status,
          String code, LocalDateTime startDate,
          LocalDateTime endDate, String sortBy, String sortAs,
          int page, int size
    ) {

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

        if(code != null && !code.isEmpty()){
            spec = spec.and((Root<CategoryModel> root, CriteriaQuery<?> query, CriteriaBuilder cb)->
                        cb.like(cb.lower(root.get("code")), "%" + code.toLowerCase() + "%")
                    );
        }

        if(startDate != null){
            spec = spec.and((Root<CategoryModel> root, CriteriaQuery<?> query, CriteriaBuilder cb)->
                        cb.between(root.get("createdAt"), startDate, endDate)
                    );
        }

//        allow key sort and sort desc or asc
        List<String> allowSort = List.of("id", "name");
//        unsorted() is by default
        Sort sort = Sort.by(Sort.Order.desc("id"));
        if(sortBy != null && sortAs != null && allowSort.contains(sortBy)){
//            equalsIgnoreCase don't worry about uppercase or lowercase
            sort = sortAs.equalsIgnoreCase("desc") ? Sort.by(Sort.Order.desc(sortBy))
                    : Sort.by(Sort.Order.asc(sortBy));
        }else {
            sort = Sort.by(Sort.Order.desc("id"));
        }

//        make pagination
        Pageable pageable = PageRequest.of(page-1 , size, sort);
//        List<CategoryModel> category = this.categoryRepository.findAll(spec,sort);
        Page<CategoryModel> category = this.categoryRepository.findAll(spec,pageable);
//        PageResponse<CategoryModel> pageResponse = PageResponse.from(category);
//        category.getContent();
//        category.getTotalPages();
//        category.getTotalElements();
//        Map<String, Object> response = new HashMap<>();
//        response.put("data", category);
        return PageResponse.from(category, this.categoryMapper::toResponse);
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
