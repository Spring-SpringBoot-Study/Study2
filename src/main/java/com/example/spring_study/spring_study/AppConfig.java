package com.example.spring_study.spring_study;

import com.example.spring_study.spring_study.discount.FixDiscountPolicy;
import com.example.spring_study.spring_study.member.MemberService;
import com.example.spring_study.spring_study.member.MemberServiceImpl;
import com.example.spring_study.spring_study.member.MemoryMemberRepository;
import com.example.spring_study.spring_study.order.OrderServiceImpl;
import com.example.spring_study.spring_study.order.OrderService;

// 객체의 생성과 연결을 담당함
public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        // OrderSe
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
