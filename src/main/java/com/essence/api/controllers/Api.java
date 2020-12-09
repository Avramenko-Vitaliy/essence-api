package com.essence.api.controllers;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Api {

    public static final String ROOT_PATH = "/essence-api";

    @UtilityClass
    public static class Build {
        public static final String VERSION = "/version";
        public static final String BUILD_TIME = "/build-time";
    }

    @UtilityClass
    public static class Session {
        public static final String LOGIN = "/login";
        public static final String LOGOUT = "/logout";
    }

    @UtilityClass
    public static class Products {
        public static final String PRODUCTS = "/products";
        public static final String PRODUCT_BY_ID = "/products/{id}";
    }

    @UtilityClass
    public static class Brands {
        public static final String BRANDS = "/brands";
    }
}
