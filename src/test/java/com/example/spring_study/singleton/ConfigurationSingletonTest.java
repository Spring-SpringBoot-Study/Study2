package com.example.spring_study.singleton;

import com.example.spring_study.AppConfig;
import com.example.spring_study.member.MemberRepository;
import com.example.spring_study.member.MemberServiceImpl;
import com.example.spring_study.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    @DisplayName("@Configuration과 싱글톤에서 객체가 여러번 new될 때, 새로 생성하는지 기존의 것을 가져오는지 테스트")
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // 참조값이 아래의 세 경우 모두 똑같음(기존의 것을 가져옴)
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("memberRepository -> memberService = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(memberRepository).isSameAs(memberRepository1);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //AppConfig도 스프링 빈으로 등록된다.
        AppConfig bean = ac.getBean(AppConfig.class);

        // bean.getClass() = class com.example.spring_study.AppConfig$$SpringCGLIB$$0
        // 이건 AppConfig에서 @Configuration 이 붙어 있기 때문에, CGLIB 객체를 만들어서 넣어줌으로서 항상 싱글톤을 보장하는 것
        // @Configuration이 빠지면, bean.getClass() = class com.example.spring_study.AppConfig 이런식으로 나옴 -> 싱클톤이 깨짐
        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
