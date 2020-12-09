package com.essence.api.controllers;

import com.essence.api.BaseControllerTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ProductsControllerTest extends BaseControllerTest {

    @Test
    public void getProducts() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .get("/essence-api/products")
                .then()
                .statusCode(SC_OK)
                .body("data", hasSize(15))
                .body("total", equalTo(15))
                .body("data[14].id", equalTo(6))
                .body("data[14].title", equalTo("Home made"))
                .body("data[14].description", equalTo("Sixth clothes"))
                .body("data[14].price", equalTo(3444.04F))
                .body("data[14].discount", equalTo(99F))
                .body("data[14].for_kids", equalTo(false))
                .body("data[14].sex_type", equalTo("WOMEN"))
                .body("data[14].clothing_type", equalTo("DRESSES"))
                .body("data[14].category_type", equalTo("CLOTHING"))
                .body("data[14].brand.id", equalTo(3))
                .body("data[14].image_ids", hasSize(4))
                .body("data[14].color_types", hasSize(1))
                .body("data[14].color_types[0]", equalTo("WHITE"))
                .body("data[14].size_types", hasSize(1))
                .body("data[14].size_types[0]", equalTo("X"))
                .body("data[14].creation_date", equalTo("2021-05-11T13:58:56.22"))
                .body("data[14].update_date", equalTo("2016-04-11T13:58:56.22"));
    }

    @Test
    public void getProductsPage() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .param("limit", 2)
                .param("offset", 0)
                .get("/essence-api/products")
                .then()
                .statusCode(SC_OK)
                .body("data", hasSize(2))
                .body("total", equalTo(15))
                .body("data[0].id", equalTo(11))
                .body("data[1].id", equalTo(5));

        given()
                .when()
                .contentType(ContentType.JSON)
                .param("limit", 2)
                .param("offset", 2)
                .get("/essence-api/products")
                .then().log().all()
                .statusCode(SC_OK)
                .body("data", hasSize(2))
                .body("total", equalTo(15))
                .body("data[0].id", equalTo(4))
                .body("data[1].id", equalTo(3));
    }

}
