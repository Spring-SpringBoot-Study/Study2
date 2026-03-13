package com.example.spring_study.spring_study.beanfind;

import com.example.spring_study.spring_study.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBeans() {
        // Spring Container에서 등록된 Bean의 이름을 꺼냄
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);

            // Key = , Value의 형태로 Spring Container에 저장되어 있음
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);

        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBeans() {
        // Spring Container에서 등록된 Bean의 이름을 꺼냄
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // ROLE_APPLICATION -> 직접 등록한 애플리케이션에서의 Bean(appConfig, memberService, memberRepository, orderService, discountPolicay 등)
            // ROLE_INFRASTRUCTURE -> 스프링이 내부에서 사용하는 Bean(springframwork..)
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);

                // Key = , Value의 형태로 Spring Container에 저장되어 있음
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            }

        }
    }

}
