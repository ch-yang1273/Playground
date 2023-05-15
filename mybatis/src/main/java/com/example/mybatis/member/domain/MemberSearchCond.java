package com.example.mybatis.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberSearchCond {

    private String name;
    private Integer birthYearBefore;
    private MemberOrderBy orderBy;
}
