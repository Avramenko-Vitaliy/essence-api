package com.essence.api.repositories.specifications;

import com.essence.api.entities.products.*;
import com.essence.api.utils.SqlHelper;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class ProductSpecifications {

    public Specification<Product> search(String search) {
        String formattedString = Arrays.stream(StringUtils.defaultIfBlank(search, "")
                .split(" "))
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .map(String::toLowerCase)
                .map(SqlHelper::formatLike)
                .collect(Collectors.joining(" "));

        if (StringUtils.isBlank(formattedString)) {
            return null;
        }
        return (root, criteriaQuery, cb) -> cb.like(cb.lower(root.get("title")), formattedString);
    }

    public Specification<Product> forChildren(Boolean forChildren) {
        if (Objects.isNull(forChildren)) {
            return null;
        }
        return (root, criteriaQuery, cb) -> cb.equal(root.get("forChildren"), forChildren);
    }

    public Specification<Product> sexType(SexType.Type type) {
        if (Objects.isNull(type)) {
            return null;
        }

        return (root, query, cb) -> cb.equal(root.get("sexTypeId"), type.getId());
    }

    public Specification<Product> sizeTypes(Set<SizeType.Type> types) {
        if (CollectionUtils.isEmpty(types)) {
            return null;
        }

        return (root, query, cb) -> {
            List<Predicate> predicates = types.stream().map(item -> cb.literal(item.getInstance()).in(root.get("sizeTypes"))).collect(Collectors.toList());
            return cb.or(predicates.toArray(new Predicate[]{}));
        };
    }

    public Specification<Product> clothingTypes(Set<ClothingType.Type> types) {
        if (CollectionUtils.isEmpty(types)) {
            return null;
        }

        return (root, query, cb) -> root.get("clothingType").get("apiKey").in(types);
    }

    public Specification<Product> colorTypes(Set<ColorType.Type> types) {
        if (CollectionUtils.isEmpty(types)) {
            return null;
        }

        return (root, query, cb) -> {
            List<Predicate> predicates = types.stream().map(item -> cb.literal(item.getInstance()).in(root.get("colorTypes"))).collect(Collectors.toList());
            return cb.or(predicates.toArray(new Predicate[]{}));
        };
    }

    public Specification<Product> categoryTypes(Set<CategoryType.Type> types) {
        if (CollectionUtils.isEmpty(types)) {
            return null;
        }

        return (root, query, cb) -> root.get("categoryType").get("apiKey").in(types);
    }

    public Specification<Product> brandIds(Set<UUID> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return null;
        }

        return (root, query, cb) -> cb.in(root.get("id")).value(ids);
    }
}
