package com.example.spring_study.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

    @Test
    @DisplayName("Stateful 상태의 싱글톤 객체 사용 오류 예시")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자가 10000원을 주문
        statefulService1.order("userA", 10000);

        // ThreadB: B사용자가 20000원을 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();

        //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
        // ThreadA와 ThreadB가 Singleton으로 StatefulService 객체를 공유하기 때문에, StatefulService 내에서 price의 값을 변경하게 코드를 작성하면 안됨
        System.out.println("price = " + price); // price = 20000

        // assertThat(statefulService1.getPrice()).isEqualTo(10000); // -> 10000을 예상했지만, 실제로는 20000이 반환되어 테스트 실패
    }
}