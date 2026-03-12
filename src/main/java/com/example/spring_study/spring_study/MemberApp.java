package com.example.spring_study.spring_study;

import com.example.spring_study.spring_study.member.Grade;
import com.example.spring_study.spring_study.member.Member;
import com.example.spring_study.spring_study.member.MemberService;
import com.example.spring_study.spring_study.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberSevice = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberSevice.join(member);

        Member findMember = memberSevice.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
