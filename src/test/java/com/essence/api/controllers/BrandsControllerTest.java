package com.essence.api.controllers;

import com.essence.api.BaseControllerTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class BrandsControllerTest extends BaseControllerTest {

    @Test
    public void getBrands() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .get("/essence-api/brands")
                .then()
                .statusCode(SC_OK)
                .body("data", hasSize(6))
                .body("total", equalTo(6))
                .body("data[0].id", equalTo(1))
                .body("data[0].logo_id", equalTo("00000000-0000-0000-0000-000000000001"))
                .body("data[0].name", equalTo("TopShop"));
    }

    @Test
    public void getBrandsPage() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .param("limit", 2)
                .param("offset", 2)
                .get("/essence-api/brands")
                .then()
                .statusCode(SC_OK)
                .body("data", hasSize(2))
                .body("total", equalTo(6))
                .body("data[0].id", equalTo(3))
                .body("data[0].logo_id", equalTo("00000000-0000-0000-0000-000000000003"))
                .body("data[0].name", equalTo("Zara"));
    }
}
