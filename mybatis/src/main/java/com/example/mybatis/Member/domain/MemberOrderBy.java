package com.example.mybatis.Member.domain;

public enum MemberOrderBy {
    NAME("name"),
    BIRTH_YEAR("birthYear")
    ;
    private final String value;

    MemberOrderBy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
