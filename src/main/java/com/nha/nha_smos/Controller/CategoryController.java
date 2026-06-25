package com.nha.nha_smos.Controller;

import ch.qos.logback.core.model.Model;
import com.nha.nha_smos.DTO.CategoryRequest;
import com.nha.nha_smos.DTO.CategoryResponse;
import com.nha.nha_smos.Model.CategoryModel;
import com.nha.nha_smos.Service.CategoryService;
import com.nha.nha_smos.Util.ApiResponse;
import jakarta.validation.Valid;
import jdk.jfr.Category;
import org.springframework.data.jpa.domain.Specification;
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
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(this.categoryService.gitList()));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<?>> filter (
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean status
    ){
//        Map<String, Object> response = new HashMap<>();
//        response.put("id", id);
//        response.put("name", name);
//        response.put("status", status);
        Map<String,Object> filterCategory = this.categoryService.filter(id, name, status);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(filterCategory));
    }




    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getList(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.ok(this.categoryService.search(id)));
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
//        @Valid help support for column undefined or create not enough column
        public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryRequest dto  ){

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.create(this.categoryService.save(dto), "Category created successfully")
            );
        }

        @PutMapping("/{id}")
        //        @Valid help support for column undefined or create not enough column
        public ResponseEntity<ApiResponse<CategoryResponse>> update(@PathVariable int id, @Valid @RequestBody CategoryRequest dto  ){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.ok(this.categoryService.update(id, dto)));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse> delete(@PathVariable int id){
            this.categoryService.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.ok(null, "Delete success"));
        }



}
