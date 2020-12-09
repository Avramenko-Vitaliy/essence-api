package com.essence.api.converters;

import com.essence.api.dtos.BrandDto;
import com.essence.api.entities.Brand;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandConverter {

    public BrandDto toDto(Brand entity) {
        return BrandDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .logoId(entity.getLogoId())
                .build();
    }
}
