package com.essence.api.controllers;

import com.essence.api.controllers.params.ProductParams;
import com.essence.api.dtos.Pagination;
import com.essence.api.dtos.ProductDto;
import com.essence.api.services.ProductsService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.essence.api.services.JwtService.JWT_TOKEN_COOKIE;

@RestController
@AllArgsConstructor
@RequestMapping(value = Api.ROOT_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController {

    private final ProductsService productsService;

    @ApiOperation("Getting products paging")
    @ApiImplicitParams({
            @ApiImplicitParam(name = JWT_TOKEN_COOKIE, paramType = "cookie"),
            @ApiImplicitParam(name = "X-XSRF-TOKEN", paramType = "header")
    })
    @ApiResponses(@ApiResponse(code = 422, message = "Unprocessable entity"))
    @GetMapping(Api.Products.PRODUCTS)
    public Pagination<ProductDto> getProducts(@Validated @ModelAttribute ProductParams params) {
        return productsService.getProducts(params);
    }

    @ApiOperation("Getting a product by id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = JWT_TOKEN_COOKIE, paramType = "cookie"),
            @ApiImplicitParam(name = "X-XSRF-TOKEN", paramType = "header")
    })
    @ApiResponses(@ApiResponse(code = 404, message = "Not Found"))
    @GetMapping(Api.Products.PRODUCT_BY_ID)
    public ProductDto getProduct(@PathVariable("id") Integer id) {
        return productsService.getProduct(id);
    }
}
