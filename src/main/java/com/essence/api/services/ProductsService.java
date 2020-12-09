package com.essence.api.services;

import com.essence.api.controllers.params.ProductParams;
import com.essence.api.converters.ProductConverter;
import com.essence.api.dtos.Pagination;
import com.essence.api.dtos.ProductDto;
import com.essence.api.entities.products.Product;
import com.essence.api.repositories.ProductsRepository;
import com.essence.api.repositories.specifications.ProductSpecifications;
import com.essence.api.repositories.specifications.SpecificationBuilder;
import com.essence.api.utils.PageUtils;
import com.essence.api.utils.SortingInfo;
import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CommonsLog
@AllArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    private static final SortingInfo SORTING_INFO = new SortingInfo(new String[]{"creationDate"})
            .addMapping("creation_date", new String[]{"creationDate"})
            .addMapping("title", new String[]{"title"})
            .addMapping("price", new String[]{"price"})
            .addMapping("discount", new String[]{"discount"});

    public Pagination<ProductDto> getProducts(ProductParams params) {
        Specification<Product> specification = new SpecificationBuilder<Product>()
                .add(ProductSpecifications.search(params.getSearch()))
                .add(ProductSpecifications.brandIds(params.getBrandId()))
                .add(ProductSpecifications.categoryTypes(params.getCategory()))
                .add(ProductSpecifications.colorTypes(params.getColor()))
                .add(ProductSpecifications.clothingTypes(params.getClothing()))
                .add(ProductSpecifications.forChildren(params.getForChildren()))
                .add(ProductSpecifications.sexType(params.getSex()))
                .add(ProductSpecifications.sizeTypes(params.getSize()))
                .build();
        Pageable pageable = PageUtils.buildPageRequest(params, SORTING_INFO.getMapping(), SORTING_INFO.getDefaultEntityValue());
        Page<Product> page = productsRepository.findAll(specification, pageable);
        List<ProductDto> data = page.stream().map(ProductConverter::toDto).collect(Collectors.toList());
        return new Pagination<>(data, page.getTotalElements());
    }

    public ProductDto getProduct(Integer id) {
        Product product = productsRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product not found!")
        );
        return ProductConverter.toDto(product);
    }
}
