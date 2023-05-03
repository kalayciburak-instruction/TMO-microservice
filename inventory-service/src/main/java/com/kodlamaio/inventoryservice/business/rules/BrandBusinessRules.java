package com.kodlamaio.inventoryservice.business.rules;

import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.inventoryservice.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository repository;

    public void checkIfBrandExists(UUID id){
        if(!repository.existsById(id)){
            throw new BusinessException("BRAND_NOT_EXISTS");
        }
    }

    public void checkIfBrandExistsByName(String name){
        if(repository.existsByNameIgnoreCase(name)){
            throw new BusinessException("BRAND_ALREADY_EXISTS");
        }
    }
}
