package com.example.mybatis.member.repository;

import com.example.mybatis.member.domain.Member;
import com.example.mybatis.member.domain.MemberSearchCond;
import com.example.mybatis.member.domain.MemberUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    int save(Member member);

    void update(@Param("id") Long id, @Param("updateParam") MemberUpdateDto dto);

    void delete(Long id);

    Optional<Member> findById(Long id);

    List<Member> findAll(MemberSearchCond cond);
}
