package com.kodlamaio.inventoryservice.business.rules;

import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.inventoryservice.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository repository;

    public void checkIfModelExists(UUID id){
        if(!repository.existsById(id)){
            throw new BusinessException("MODEL_NOT_EXISTS");
        }
    }
}
