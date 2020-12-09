package com.essence.api.utils;

import lombok.experimental.UtilityClass;

import static org.apache.commons.lang3.StringUtils.isBlank;

@UtilityClass
public class SqlHelper {

    public static String formatLike(String searchStr) {
        return isBlank(searchStr) ? "%" : String.format("%%%s%%", searchStr);
    }

    public static String formatStartsWith(String searchStr) {
        return isBlank(searchStr) ? "%" : String.format("%s%%", searchStr);
    }
}
