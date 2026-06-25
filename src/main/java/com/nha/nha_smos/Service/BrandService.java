package com.nha.nha_smos.Service;

import com.nha.nha_smos.DTO.BrandRequest;
import com.nha.nha_smos.DTO.BrandResponse;
import com.nha.nha_smos.Exception.ResourceNotFoundException;
import com.nha.nha_smos.Mapper.BrandMapper;
import com.nha.nha_smos.Model.BrandModel;
import com.nha.nha_smos.Model.RoleModel;
import com.nha.nha_smos.Repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper mapper;


    public List<BrandResponse> getBrand(){

        return brandRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public BrandResponse search(int id){
//        BrandModel brandModel = brandRepository.findById(id)
//                .orElseThrow(()->new RuntimeException("Brand NOT FOUND!"));
        BrandModel brandModel = brandRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Brand NOT FOUND!" + " " + id));
        return mapper.toResponse(brandModel);
    }

    public BrandResponse createBrand(BrandRequest brandRequest){
        if(brandRepository.existsByCode(brandRequest.getCode())){
            throw new ResourceNotFoundException("Brand already exists!");
        }
        // request -> entity
        BrandModel brandModel = mapper.toEntity(brandRequest);
        // save new recode
        BrandModel saved = brandRepository.save(brandModel);
        // saved return all column from
        return mapper.toResponse(saved);
        //entity from  db -> mapper to response
    }

    public BrandResponse updateBrand(int id, BrandRequest Request){
        BrandModel brandModel = brandRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Brand NOT FOUND!" + id));
        brandModel.setName(Request.getName());
        brandModel.setDescription(Request.getDescription());
        brandModel.setCode(Request.getCode());
        brandModel.setStatus(Request.getStatus());
        brandModel.setUpdatedBy(Request.getUpdatedBy());
        BrandModel update = brandRepository.save(brandModel);
        return mapper.toResponse(update);
    }

    public void deleteBrand(int id){
        if(brandRepository.existsById(id)){
            brandRepository.deleteById(id);
        }
    }
}
