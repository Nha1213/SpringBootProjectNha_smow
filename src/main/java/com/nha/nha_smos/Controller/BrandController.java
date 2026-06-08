package com.nha.nha_smos.Controller;

import com.nha.nha_smos.DTO.BrandRequest;
import com.nha.nha_smos.DTO.BrandResponse;
import com.nha.nha_smos.Model.BrandModel;
import com.nha.nha_smos.Repository.BrandRepository;
import com.nha.nha_smos.Service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public List<BrandResponse> getBrand(){
        return brandService.getBrand();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable int id){
        return ResponseEntity.ok(brandService.search(id));
    }

    @PostMapping
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest){
        return ResponseEntity.ok(brandService.createBrand(brandRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable int id, @RequestBody BrandRequest Request){
        return ResponseEntity.ok(brandService.updateBrand(id, Request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> DeleteBrand(@PathVariable int id){
        brandService.deleteBrand(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Brand deleted successfully");
        return ResponseEntity.ok(response);
    }
}
