package com.playground.testing.testinghelpers;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemberRepository {

    Map<Long, Member> map = new HashMap<>();

    public void save(Member member) {
        map.put(member.getId(), member);
    }
}
