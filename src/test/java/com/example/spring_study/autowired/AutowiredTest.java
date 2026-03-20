package com.example.spring_study.autowired;

import com.example.spring_study.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        // 방법 1: 아예 호출 안 함 - TestBean 안에는 Mmeber가 없음 -> 수정자 메소드 자체가 호출이 안됨
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 방법 2: null 주입 -> 메소드는 호출이 되는데 객체에 null로 들어옴
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 방법 3: Optional.empty 주입 (Optional로 감싸기 필요) -> spring bean이 없으면 Optional.empty가 들어옴
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3); // noBean3 = Optional.empty
        }
    }
}
