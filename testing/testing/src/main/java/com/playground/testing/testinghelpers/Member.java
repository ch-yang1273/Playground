package com.playground.testing.testinghelpers;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    private static Long NEXT_ID = 0L;

    @Id
    private Long id;

    private String name;

    private String email;

    @Builder
    public Member(String name, String email) {
        this.id = NEXT_ID;
        this.name = name;
        this.email = email;
        NEXT_ID++;
    }
}
