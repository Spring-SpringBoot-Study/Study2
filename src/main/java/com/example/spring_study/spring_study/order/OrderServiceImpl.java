package com.example.spring_study.spring_study.order;

import com.example.spring_study.spring_study.discount.DiscountPolicy;
import com.example.spring_study.spring_study.discount.FixDiscountPolicy;
import com.example.spring_study.spring_study.member.Member;
import com.example.spring_study.spring_study.member.MemberRepository;
import com.example.spring_study.spring_study.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderSevice{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 단일 책임 원칙(SRP)
        // OrderService 입장에서는 discount에 대한 사항을 모름 -> 책임을 DiscountPolicy한테 인가
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
