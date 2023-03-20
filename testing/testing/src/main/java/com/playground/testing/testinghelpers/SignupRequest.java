package com.playground.testing.testinghelpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignupRequest {

    private String name;
    private String email;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }
}
