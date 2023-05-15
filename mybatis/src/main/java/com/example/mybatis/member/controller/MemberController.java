package com.example.mybatis.member.controller;

import com.example.mybatis.member.dto.MemberCreateRequest;
import com.example.mybatis.member.dto.MemberResponse;
import com.example.mybatis.member.dto.MemberSearchRequest;
import com.example.mybatis.member.dto.MemberUpdateRequest;
import com.example.mybatis.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest dto) {
        memberService.createMember(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/{memberId}")
    public ResponseEntity<Void> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequest dto) {
        memberService.updateMember(memberId, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok().body(memberService.getMember(memberId));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getMembers(@RequestParam(required = false) MemberSearchRequest cond) {
        return ResponseEntity.ok().body(memberService.getMembers(cond));
    }
}
