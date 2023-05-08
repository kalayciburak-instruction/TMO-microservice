package com.kodlamaio.filterservice.business.abstracts;

import com.kodlamaio.filterservice.business.dto.responses.GetAllFiltersResponse;
import com.kodlamaio.filterservice.business.dto.responses.GetFilterResponse;
import com.kodlamaio.filterservice.entities.Filter;

import java.util.List;
import java.util.UUID;

public interface FilterService {
    List<GetAllFiltersResponse> getAll();
    GetFilterResponse getById(UUID id);
    Filter getByCarId(UUID carId);
    List<Filter> getByModelId(UUID modelId);
    List<Filter> getByBrandId(UUID brandId);
    void save(Filter filter);
    void delete(UUID id);
    void deleteByCarId(UUID carId);
    void deleteAllByBrandId(UUID brandId);
    void deleteAllByModelId(UUID modelId);
}
