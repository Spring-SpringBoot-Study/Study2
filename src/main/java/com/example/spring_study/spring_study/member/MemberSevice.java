package com.example.spring_study.spring_study.member;

public interface MemberSevice {

    void join(Member member);

    Member findMember(Long memberId);
}
