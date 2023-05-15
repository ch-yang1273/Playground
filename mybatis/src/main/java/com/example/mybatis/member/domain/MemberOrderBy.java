package com.example.mybatis.member.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MemberOrderBy {
    NAME("NAME ASC", "name"),
    BIRTH("BIRTH_YEAR ASC", "year"),
    ;
    private final String orderCriteria;
    private final String value;

    MemberOrderBy(String orderCriteria, String value) {
        this.orderCriteria = orderCriteria;
        this.value = value;
    }

    public String getOrderCriteria() {
        return orderCriteria;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}