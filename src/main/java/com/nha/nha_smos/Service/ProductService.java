package com.nha.nha_smos.Service;

import com.nha.nha_smos.DTO.PageResponse;
import com.nha.nha_smos.DTO.ProductRequest;
import com.nha.nha_smos.DTO.ProductResponse;
import com.nha.nha_smos.Exception.ResourceNotFoundException;
import com.nha.nha_smos.Mapper.ProductMapper;
import com.nha.nha_smos.Model.BrandModel;
import com.nha.nha_smos.Model.CategoryModel;
import com.nha.nha_smos.Model.ProductModel;
import com.nha.nha_smos.Repository.BrandRepository;
import com.nha.nha_smos.Repository.CategoryRepository;
import com.nha.nha_smos.Repository.ProductRepository;
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

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductMapper productMapper;

    public ProductResponse saveProduct(ProductRequest req) {

        CategoryModel category = this.categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not Found!"));
        BrandModel brand = this.brandRepository.findById(Math.toIntExact(req.getBrandId()))
                .orElseThrow(() -> new ResourceNotFoundException("Brand Not Found!"));
        ProductModel  product =  this.productMapper.toEntity(req, category,brand);
        this.productRepository.save(product);
        return productMapper.toResponse(product);
    }

    public List<ProductResponse> list(){
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return productRepository.findAll(sort)
                .stream().map(productMapper::toResponse)
                .toList();
    }

    public  ProductResponse listById(int id){
        ProductModel  product =  this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));
        return productMapper.toResponse(product);
    }

    public PageResponse<ProductResponse> filter(Long id, String productName, Boolean status,
                                                String code, LocalDateTime startDate,
                                                LocalDateTime endDate, String sortBy, String sortAs,
                                                int page, int size)
    {

        Specification<ProductModel> spec = Specification.unrestricted();

        if(id != null){
            spec = spec.and((Root<ProductModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                        cb.equal(root.get("id"), id)
                    );
        }

        if( productName != null && !productName.isEmpty() ){
            spec = spec.and((Root<ProductModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                        cb.like(cb.lower(root.get("productName")), "%" + productName.toLowerCase() + "%")
                    );
        }

        if( status != null){
            spec = spec.and((Root<ProductModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                        cb.equal(root.get("status"), status)
                    );
        }

        if( code != null && !code.isEmpty() ){
            spec = spec.and((Root<ProductModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                        cb.like(cb.lower(root.get("code")), "%" + code.toLowerCase() + "%" )
                    );
        }

        if(startDate != null){
            spec = spec.and((Root<ProductModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) ->
                        cb.between(root.get("startDate"), startDate, endDate)
                    );
        }

        List<String> allowSort = List.of("id", "productName");
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        if(sortBy != null && sortAs != null && allowSort.contains(sortBy)){
            sort = sortAs.equalsIgnoreCase("desc") ? Sort.by(Sort.Order.desc((sortBy)))
            : Sort.by(Sort.Order.asc((sortBy)));
        }
        else {
            sort = Sort.by(Sort.Direction.DESC, "id");
        }

        Pageable pageable = PageRequest.of(page - 1, size, sort );

        Page<ProductModel> product = this.productRepository.findAll(spec, pageable);
        return PageResponse.from(product, this.productMapper::toResponse);
    }


    public ProductResponse update(int id, ProductRequest req) {
        ProductModel product =   this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));

        BrandModel brand = this.brandRepository.findById(Math.toIntExact(req.getBrandId()))
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Brand Not Found!"));

        CategoryModel category = this.categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
        product.setProductName(req.getProductName());
        product.setPrice(req.getPrice());
        product.setPrice(req.getPrice());
        product.setDescription(req.getDescription());
        product.setStatus(req.getStatus());
        product.setBrand(brand);
        product.setCategory(category);
//         use mapper is create new record
//        product = this.productMapper.toEntity(req, category, brand);

        this.productRepository.save(product);
        return productMapper.toResponse(product);
    }


    public void delete(int id){
        this.productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Delete Product Not Found!"));
        this.productRepository.deleteById(id);
    }

}
