package com.nha.nha_smos.Controller;

import ch.qos.logback.core.model.Model;
import com.nha.nha_smos.DTO.CategoryRequest;
import com.nha.nha_smos.DTO.CategoryResponse;
import com.nha.nha_smos.Service.CategoryService;
import com.nha.nha_smos.Util.ApiResponse;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequiredArgsConstructor
@Component
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }


    @GetMapping
    public ResponseEntity<List<CategoryResponse>> list(){
        return ResponseEntity.ok(this.categoryService.gitList());
    }

//    @PostMapping
//    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest dto  ){
//        return ResponseEntity.ok(this.categoryService.save(dto));
//    }
//    @PostMapping
//    public ResponseEntity<?> create(@RequestBody CategoryRequest dto  ){
//        Map<String,Object> res = new HashMap<>();
//        res.put("success", true);
//        res.put("code", HttpStatus.OK.value());
//        res.put("message", "Category created successfully");
//        res.put("errors", null);
//        res.put("data", this.categoryService.save(dto));
//        return ResponseEntity.status(HttpStatus.CREATED.value()).body(res);
//    }
        @PostMapping
        public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryRequest dto  ){

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.create(this.categoryService.save(dto), "Category created successfully")
            );
        }




}
