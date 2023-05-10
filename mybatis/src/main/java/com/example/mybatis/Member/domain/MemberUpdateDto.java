package com.example.mybatis.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberUpdateDto {

    private String name;
    private Integer birthYear;
}
