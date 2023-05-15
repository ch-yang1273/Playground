package com.example.mybatis.member.dto;

import com.example.mybatis.member.domain.MemberOrderBy;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberSearchRequest {

    private String name;
    @JsonProperty("birth")
    private Integer birthYear;
    private MemberOrderBy order;
}
