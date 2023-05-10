package com.example.mybatis.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member {

    private Long id;

    private String name;
    private Integer birthYear;
}
