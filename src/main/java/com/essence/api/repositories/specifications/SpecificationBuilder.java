package com.essence.api.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SpecificationBuilder<T> {

    private List<Specification<T>> specifications = new ArrayList<>();

    public SpecificationBuilder<T> add(Specification<T> specification) {
        if (Objects.nonNull(specification)) {
            specifications.add(specification);
        }
        return this;
    }

    public Specification<T> build() {
        return specifications.stream()
                .filter(Objects::nonNull)
                .reduce(Specification.where(null), Specification::and);
    }
}
