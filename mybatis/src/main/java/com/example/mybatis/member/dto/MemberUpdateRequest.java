package com.example.mybatis.member.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberUpdateRequest {

    private String name;
    private Integer birthYear;

    @JsonCreator
    public MemberUpdateRequest(@JsonProperty("name") String name, @JsonProperty("birth") Integer birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }
}
