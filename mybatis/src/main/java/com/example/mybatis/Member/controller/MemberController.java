package com.example.mybatis.Member.controller;

import com.example.mybatis.Member.dto.MemberUpdateRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {

    @PostMapping("/create")
    public void createMember() {
    }

    @PostMapping("/update/{memberId}")
    public void updateMember(@PathVariable Long memberId, MemberUpdateRequest updateRequest) {
    }

    @GetMapping("/{memberId}")
    public void getMember() {
    }

    @GetMapping
    public void getMembers() {
    }
}
