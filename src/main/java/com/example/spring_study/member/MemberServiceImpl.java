package com.example.spring_study.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;    

    // Component 등록을 할 때, 자동으로 의존관계 주입을 위하 Autowired를 사용
    // 기존 ac.getBean(MemberRepository.class)와 유사
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // @Configuration과 싱글톤 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
