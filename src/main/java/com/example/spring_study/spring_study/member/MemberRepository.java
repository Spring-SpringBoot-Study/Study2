package com.example.spring_study.spring_study.member;

public interface MemberRepository {

    void save(Member member);

    Member findById(Long id);
}
