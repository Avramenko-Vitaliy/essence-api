package com.essence.api.utils;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Getter
public class SortingInfo {

    private Map<String, String[]> mapping = new HashMap<>();
    private String[] defaultEntityValue;

    public SortingInfo(String[] defaultEntityValue) {
        this.defaultEntityValue = defaultEntityValue;
    }

    public SortingInfo addMapping(String order, String[] entityValue) {
        mapping.put(StringUtils.lowerCase(order), entityValue);
        return this;
    }
}
