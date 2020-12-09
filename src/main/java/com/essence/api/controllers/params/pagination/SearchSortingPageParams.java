package com.essence.api.controllers.params.pagination;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SearchSortingPageParams extends SortingPageParams {

    private String search;
}
