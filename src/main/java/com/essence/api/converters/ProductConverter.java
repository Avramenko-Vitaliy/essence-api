package com.essence.api.converters;

import com.essence.api.dtos.ProductDto;
import com.essence.api.entities.products.ColorType;
import com.essence.api.entities.products.Product;
import com.essence.api.entities.products.SizeType;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ProductConverter {

    public ProductDto toDto(Product entity) {
        Set<ColorType.Type> colorTypes = entity.getColorTypes().stream().map(ColorType::getApiKey).collect(Collectors.toSet());
        Set<SizeType.Type> sizeTypes = entity.getSizeTypes().stream().map(SizeType::getApiKey).collect(Collectors.toSet());

        return ProductDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .discount(entity.getDiscount())
                .forKids(Boolean.TRUE.equals(entity.getForKids()))
                .sexType(entity.getSexType().getApiKey())
                .clothingType(entity.getClothingType().getApiKey())
                .categoryType(entity.getCategoryType().getApiKey())
                .sizeTypes(sizeTypes)
                .colorTypes(colorTypes)
                .imageIds(entity.getImageIds())
                .brand(BrandConverter.toDto(entity.getBrand()))
                .creationDate(entity.getCreationDate())
                .updateDate(entity.getUpdateDate())
                .build();
    }
}
