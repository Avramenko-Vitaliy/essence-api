package com.essence.api.controllers.params.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SortingPageParams extends PageParams {

    @JsonProperty("asc")
    @ApiParam("Sorts in ascending(true) or descending(false) order. By default - true.")
    private boolean asc = true;

    private String sort;

}
