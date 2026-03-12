package com.example.spring_study.spring_study;

import com.example.spring_study.spring_study.member.Grade;
import com.example.spring_study.spring_study.member.Member;
import com.example.spring_study.spring_study.member.MemberSevice;
import com.example.spring_study.spring_study.member.MemberSeviceImpl;
import com.example.spring_study.spring_study.order.Order;
import com.example.spring_study.spring_study.order.OrderServiceImpl;
import com.example.spring_study.spring_study.order.OrderSevice;

public class OrderApp {
    public static void main(String[] args) {
        MemberSevice memberSevice = new MemberSeviceImpl();
        OrderSevice orderSevice = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberSevice.join(member);

        Order order = orderSevice.createOrder(memberId,"itemA", 10000);

        System.out.println("order = " + order);
        // System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
