package com.example.spring_study.spring_study;

import com.example.spring_study.spring_study.member.Grade;
import com.example.spring_study.spring_study.member.Member;
import com.example.spring_study.spring_study.member.MemberSevice;
import com.example.spring_study.spring_study.member.MemberSeviceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberSevice memberSevice = new MemberSeviceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberSevice.join(member);

        Member findMember = memberSevice.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
