package com.example.spring_study.discount;

import com.example.spring_study.member.Grade;
import com.example.spring_study.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Qualifier("mainDiscountPolicy")
@Primary // -> Bean 타입이 겹칠 경우, 최상위 우선순위를 가지게 함
// @Qualifire vs @Primary 의 선택이지만, 둘 중에서 우선순위는 @Qualifire가 가져감(Spring은 자동보다는 수동이, 넓은 범위보다 좁은 범위의 선택권이 우선순위임)
public class RateDiscountPolicy implements DiscountPolicy {

    private int dicountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * dicountPercent / 100;
        }else {
            return 0;
        }
    }
}
