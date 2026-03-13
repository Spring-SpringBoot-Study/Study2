package com.example.spring_study;

import com.example.spring_study.discount.DiscountPolicy;
import com.example.spring_study.discount.RateDiscountPolicy;
import com.example.spring_study.member.MemberRepository;
import com.example.spring_study.member.MemberService;
import com.example.spring_study.member.MemberServiceImpl;
import com.example.spring_study.member.MemoryMemberRepository;
import com.example.spring_study.order.OrderServiceImpl;
import com.example.spring_study.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 객체의 생성과 연결을 담당함
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemberServiceImpl(memberRepository())의 memberRepositoy()에서 new MemoryMemberRepository 호출해서 객체 생성
    // @Bean orderService -> new OrderServiceImpl(memberRepository(), discountPolicy())의 memberRepositoy()에서 new MemoryMemberRepository 호출해서 객체 생성
    // -> 객체가 2번 생성되는데, Singleton이 깨질까? 이거 괜찮나?

    // 각 메서드의 역할이 잘 들어나게 리팩터링
    @Bean // Spring Container에 Bean으로 등록됨
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        // 이 부분만 바꾸면, 할인 방식을 바꿀 수 있음(사용 영역의 코드는 바꿀 필요 없이, 구성 영역의 코드만 바꾸면 됨)
        return new RateDiscountPolicy();
    }
}
