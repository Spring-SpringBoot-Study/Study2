package com.example.spring_study;

import com.example.spring_study.member.Grade;
import com.example.spring_study.member.Member;
import com.example.spring_study.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // 기존 MemberService memberService = new MemberServiceImpl()을 대체 -> AppConfig의 생성자로 memberService 객체를 가져옴
        // MemberService memberService = appConfig.memberService();

        // Spring에서는 ApplicationContext가 Spring Container임
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
