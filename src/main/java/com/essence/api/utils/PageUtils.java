package com.essence.api.utils;

import com.essence.api.controllers.params.pagination.PageParams;
import com.essence.api.controllers.params.pagination.SortingPageParams;
import com.essence.api.repositories.OffsetBasedPageRequest;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class PageUtils {

    public static Pageable buildPageRequest(SortingPageParams params, Map<String, String[]> mappedSort, String[] defaultSort) {
        String[] sortFields = mappedSort.getOrDefault(params.getSort(), defaultSort);
        List<Sort.Order> orders = Stream.of(sortFields)
                .map(sortField -> params.isAsc()
                        ? Sort.Order.asc(sortField).nullsLast()
                        : Sort.Order.desc(sortField).nullsFirst()
                )
                .collect(Collectors.toList());
        Sort sort = Sort.by(orders).and(Sort.by("id"));

        return OffsetBasedPageRequest.builder()
                .limit(params.getLimit())
                .offset(params.getOffset())
                .sort(sort)
                .build();
    }

    public static Pageable buildPageRequest(PageParams params) {
        return OffsetBasedPageRequest.builder()
                .limit(params.getLimit())
                .offset(params.getOffset())
                .sort(Sort.by(Sort.Order.asc("id")))
                .build();
    }
}
