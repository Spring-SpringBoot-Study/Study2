package com.example.spring_study.spring_study;

import com.example.spring_study.spring_study.discount.DiscountPolicy;
import com.example.spring_study.spring_study.discount.FixDiscountPolicy;
import com.example.spring_study.spring_study.discount.RateDiscountPolicy;
import com.example.spring_study.spring_study.member.MemberRepository;
import com.example.spring_study.spring_study.member.MemberService;
import com.example.spring_study.spring_study.member.MemberServiceImpl;
import com.example.spring_study.spring_study.member.MemoryMemberRepository;
import com.example.spring_study.spring_study.order.OrderServiceImpl;
import com.example.spring_study.spring_study.order.OrderService;

// 객체의 생성과 연결을 담당함
public class AppConfig {

    // 각 메서드의 역할이 잘 들어나게 리팩터링
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        // 이 부분만 바꾸면, 할인 방식을 바꿀 수 있음(사용 영역의 코드는 바꿀 필요 없이, 구성 영역의 코드만 바꾸면 됨)
        return new RateDiscountPolicy();
    }
}
