package com.example.spring_study.xml;

import com.example.spring_study.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// xml 기반으로도 AppConfig.java에서 했던 Spring Container의 설정을 넣을 수 있음(요즘에는 xml방식이 아닌, AppConfig.java의 방식으로 대부분 함)
public class XmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
