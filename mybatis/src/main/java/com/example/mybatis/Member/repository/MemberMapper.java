package com.example.mybatis.Member.repository;

import com.example.mybatis.Member.domain.Member;
import com.example.mybatis.Member.domain.MemberSearchCond;
import com.example.mybatis.Member.domain.MemberUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    Member save(Member member);

    void update(Long id, MemberUpdateDto dto);

    Optional<Member> findById(Long id);

    List<Member> findAll(MemberSearchCond cond);
}
