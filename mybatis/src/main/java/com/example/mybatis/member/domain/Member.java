package com.example.mybatis.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Member {

    private Long id;

    private String name;
    private Integer birthYear;
}
