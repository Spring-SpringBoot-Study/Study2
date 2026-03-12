package com.example.spring_study.spring_study;

import com.example.spring_study.spring_study.member.Grade;
import com.example.spring_study.spring_study.member.Member;
import com.example.spring_study.spring_study.member.MemberService;
import com.example.spring_study.spring_study.order.Order;
import com.example.spring_study.spring_study.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        // AppConfig appConfig = new AppConfig();
        // MemberService memberService = appConfig.memberService(); // MemberService memberService = new MemberServiceImpl()을 대체
        // OrderService orderService = appConfig.orderService(); // OrderService orderService = new OrderServiceImpl()을 대체

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA", 10000);

        System.out.println("order = " + order);
        // System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
