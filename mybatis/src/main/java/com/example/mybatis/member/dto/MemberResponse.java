package com.example.mybatis.member.dto;

import com.example.mybatis.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberResponse {

    private Long id;
    private String name;
    private Integer birthYear;

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getName(), member.getBirthYear());
    }
}
