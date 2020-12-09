package com.essence.api.controllers.params.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PageParams {

    @ApiParam("OFFSET indicates to skip the specified number of rows. " +
            "By default, OFFSET = 0 is equivalent to the absence of indication of OFFSET. Must be positive or zero.")
    @JsonProperty("offset")
    @PositiveOrZero
    private int offset = 0;

    @ApiParam("If the number LIMIT is specified, the result is no more than the specified number of rows " +
            "(less can be if the query itself produced fewer lines). By default, LIMIT = 15. Must be positive.")
    @JsonProperty("limit")
    @Positive
    private int limit = 15;
}
