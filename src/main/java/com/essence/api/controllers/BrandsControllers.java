package com.essence.api.controllers;

import com.essence.api.controllers.params.pagination.PageParams;
import com.essence.api.dtos.Pagination;
import com.essence.api.dtos.BrandDto;
import com.essence.api.services.BrandsService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.essence.api.services.JwtService.JWT_TOKEN_COOKIE;

@RestController
@AllArgsConstructor
@RequestMapping(value = Api.ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class BrandsControllers {

    private final BrandsService brandsService;

    @ApiOperation("Getting brands paging")
    @ApiImplicitParams({
            @ApiImplicitParam(name = JWT_TOKEN_COOKIE, paramType = "cookie"),
            @ApiImplicitParam(name = "X-XSRF-TOKEN", paramType = "header")
    })
    @ApiResponses(@ApiResponse(code = 422, message = "Unprocessable entity"))
    @GetMapping(Api.Brands.BRANDS)
    public Pagination<BrandDto> getBrands(@Validated @ModelAttribute PageParams params) {
        return brandsService.getBrands(params);
    }
}
