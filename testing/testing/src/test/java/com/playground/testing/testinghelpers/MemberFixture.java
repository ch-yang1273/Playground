package com.playground.testing.testinghelpers;

public enum MemberFixture {

    BLOO("blooUser", "bloo@gmail.com")
    ;

    private final String name;
    private final String email;

    MemberFixture(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Member toMember() {
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }

    public void 회원가입을한다() {

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
