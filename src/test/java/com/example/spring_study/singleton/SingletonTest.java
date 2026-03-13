package com.example.spring_study.singleton;

import com.example.spring_study.AppConfig;
import com.example.spring_study.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        // 순수 자바 방식으로 AppConfig 사용(AppConfig에 어노테이션(@)을 이용하여 작성되어 있더라고, Spring 방식(ApplicationContext)으로 불러오지 않으면 효력 x)
        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 떄마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 떄마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        // 메모리에 객체가 계속 생성됨(웹은 여러 사용자가 요청을 계속 보내는 구조인데, 이러면 객체가 계속 쌓임)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

        // 이렇게 호출할 때마다 객체를 생성하는 문제를 해결하기 위해서 객체를 1개만 생성하는 Singleton 방식을 사용
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        // new SingletonService(); // -> private이기 때문에 이 방식으로 싱글톤 객체 생성 시도를 막음
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은 것을 확인
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        // same -> 인스턴스가 같은지
        // equal = 내용이 같은지
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        // AppConfig appConfig = new AppConfig(); // 순수 자바 방식
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // Spring 방식

        //1. 조회 : 호출할 떄마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);

        //2. 조회 : 호출할 떄마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        // 참조값이 같은 것을 확인(Spring의 Spring Container에서는 Bean을 자동으로 Singleton 패턴으로 관리)
        // Spring의 기본 Bean 등록 방식은 Singleton이지만, 요청할 때마다 새로운 객체를 생성해서 반환하는 기능도 제공(이후 Bean Scope에서 다룸)
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 ==  memberService2
        assertThat(memberService1).isSameAs(memberService2);

        // 이렇게 호출할 때마다 객체를 생성하는 문제를 해결하기 위해서 객체를 1개만 생성하는 Singleton 방식을 사용
    }
}
