package com.playground.testing.testinghelpers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private MemberRepository memberRepository;

    public void save(SignupRequest signupRequest) {
    }
}
