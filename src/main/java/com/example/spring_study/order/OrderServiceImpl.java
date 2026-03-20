package com.example.spring_study.order;

import com.example.spring_study.discount.DiscountPolicy;
import com.example.spring_study.member.Member;
import com.example.spring_study.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // Lombock 라이브러리에서 final이 붙은 객체를 파라메터로 받는 생성자를 자동으로 만들어줌
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자 주입 방식을 사용하면, final 키워드를 사용할 수 있다.
    // final은 선언 직후에 바로 초기화가 이루어져야하므로, 개발자가 실수 생성자 코드 미작성 시에 컴파일 오류로 식별 가능
    // 생성자가 1개이므로, @Autowired 생략 가능
    // @RequiredArgsConstructor으로 아래의 생성자를 자동으로 만들어줌
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        // 단일 책임 원칙(SRP)
        // OrderService 입장에서는 discount에 대한 사항을 모름 -> 책임을 DiscountPolicy한테 인가
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // @Configuration과 싱글톤 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
