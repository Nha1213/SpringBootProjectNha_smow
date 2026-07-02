package com.nha.nha_smos.Controller;

import com.nha.nha_smos.DTO.PageResponse;
import com.nha.nha_smos.DTO.ProductRequest;
import com.nha.nha_smos.DTO.ProductResponse;
import com.nha.nha_smos.Model.ProductModel;
import com.nha.nha_smos.Service.ProductService;
import com.nha.nha_smos.Util.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> list(){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(this.productService.list()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> listById(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(this.productService.listById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> saveProduct(@Valid @RequestBody ProductRequest req){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.create(productService.saveProduct(req)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable int id, @Valid @RequestBody ProductRequest req){
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.ok(this.productService.update(id, req), "product update success"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> deleteProduct(@PathVariable int id){
        this.productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.ok(null, "Product deleted successfully"));
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> listByFilter(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Boolean status,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortAs,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size


    ){
            PageResponse<ProductResponse> filterProduct = this.productService.filter(
                    id, productName, status, code, startDate, endDate, sortBy, sortAs, page, size
            );

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ApiResponse.ok(filterProduct));

    }

}
