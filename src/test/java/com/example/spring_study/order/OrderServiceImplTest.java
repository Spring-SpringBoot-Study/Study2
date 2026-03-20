package com.example.spring_study.order;

import com.example.spring_study.discount.FixDiscountPolicy;
import com.example.spring_study.member.Grade;
import com.example.spring_study.member.Member;
import com.example.spring_study.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        // 이처럼 생성자 주입 방식을 사용할 시, Spring 없이 순순한 자바 코드로 테스트를 만들 수 있음
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
