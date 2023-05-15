package com.example.mybatis.member.service;

import com.example.mybatis.member.domain.Member;
import com.example.mybatis.member.domain.MemberSearchCond;
import com.example.mybatis.member.domain.MemberUpdateDto;
import com.example.mybatis.member.dto.*;
import com.example.mybatis.member.repository.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;

    public void createMember(MemberCreateRequest dto) {
        Member newMember = new Member(null, dto.getName(), dto.getBirthYear());
        int count = memberMapper.save(newMember);
        log.info("create Member. id={}", newMember.getId());
    }

    public void updateMember(Long memberId, MemberUpdateRequest dto) {
        MemberUpdateDto updateDto = new MemberUpdateDto(dto.getName(), dto.getBirthYear());
        memberMapper.update(memberId, updateDto);
    }

    public void deleteMember(Long memberId) {
        memberMapper.delete(memberId);
    }

    public MemberResponse getMember(Long memberId) {
        Member member = memberMapper.findById(memberId).orElseThrow();
        return MemberResponse.of(member);
    }

    public List<MemberResponse> getMembers(MemberSearchRequest cond) {
        if (cond == null) {
            cond = new MemberSearchRequest();
        }

        MemberSearchCond memberSearchCond = new MemberSearchCond(cond.getName(), cond.getBirthYear(), cond.getOrder());

        return memberMapper.findAll(memberSearchCond).stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());
    }
}
