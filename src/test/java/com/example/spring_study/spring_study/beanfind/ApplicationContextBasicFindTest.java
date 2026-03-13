package com.example.spring_study.spring_study.beanfind;

import com.example.spring_study.spring_study.AppConfig;
import com.example.spring_study.spring_study.member.MemberService;
import com.example.spring_study.spring_study.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        // System.out.println("memberService = " + memberService);
        // System.out.println("memberService.getClass() = " + memberService.getClass());

        // MemerService가 MemberServiceImpl의 인스턴스인지
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로 조회")
    void findBeanByType(){
        // 같은 타입이 여러 개의 경우 위험(하나일 경우에는 가능)
        MemberService memberService = ac.getBean(MemberService.class);

        // MemerService가 MemberServiceImpl의 인스턴스인지
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    // 구체에 의존하는 형식이기 때문에, DIP에 위반 -> 좋은 코드는 아님
    void findBeanByType2(){
        MemberServiceImpl memberServiceImpl = ac.getBean("memberService", MemberServiceImpl.class);

        // MemerService가 MemberServiceImpl의 인스턴스인지
        assertThat(memberServiceImpl).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 x")
    void findBeanByNameX(){
        // ac.getBean("xxxxx", MemberService.class);
        // MemberService xxxxx = ac.getBean("xxxxx", MemberService.class); -> 실행시에 NoSuchBeanDefinitionException 에러가 나옴
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
    }
}
