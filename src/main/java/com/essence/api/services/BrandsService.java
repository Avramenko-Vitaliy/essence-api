package com.essence.api.services;

import com.essence.api.controllers.params.pagination.PageParams;
import com.essence.api.dtos.Pagination;
import com.essence.api.converters.BrandConverter;
import com.essence.api.dtos.BrandDto;
import com.essence.api.entities.Brand;
import com.essence.api.repositories.BrandsRepository;
import com.essence.api.utils.PageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CommonsLog
@AllArgsConstructor
public class BrandsService {

    private final BrandsRepository brandsRepository;

    public Pagination<BrandDto> getBrands(PageParams params) {
        Page<Brand> page = brandsRepository.findAll(PageUtils.buildPageRequest(params));
        List<BrandDto> data = page.stream().map(BrandConverter::toDto).collect(Collectors.toList());
        return new Pagination<>(data, page.getTotalElements());
    }
}
