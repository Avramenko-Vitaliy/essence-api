package com.essence.api.controllers.params;

import com.essence.api.controllers.params.pagination.SearchSortingPageParams;
import com.essence.api.entities.products.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductParams extends SearchSortingPageParams {

    private Boolean forChildren;
    private SexType.Type sex;
    private Set<SizeType.Type> size;
    private Set<ClothingType.Type> clothing;
    private Set<ColorType.Type> color;
    private Set<CategoryType.Type> category;
    private Set<UUID> brandId;
}
