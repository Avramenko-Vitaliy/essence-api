package com.essence.api.controllers.params.pagination;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SearchPageParams extends PageParams {

    private String search;
}
