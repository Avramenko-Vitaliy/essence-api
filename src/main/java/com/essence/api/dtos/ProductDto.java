package com.essence.api.dtos;

import com.essence.api.entities.products.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Integer id;
    private String title;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private Boolean forKids;
    private SexType.Type sexType;
    private ClothingType.Type clothingType;
    private CategoryType.Type categoryType;
    private BrandDto brand;

    private Set<UUID> imageIds;
    private Set<ColorType.Type> colorTypes;
    private Set<SizeType.Type> sizeTypes;

    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
}
