package com.example.spring_study.scan.filter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull(); // beanA는 @MyIncludeComponent이 붙은 클래스이므로 Scan이 됨

        // ac.getBean("beanB", BeanA.class); // beanA는 @MyExcludeComponent이 붙은 클래스이므로 Scan이 안됨 -> NoSuchBeanDefinitionException 에러

        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanA.class));

    }

    @Configuration
    @ComponentScan(
            // type = FilterType.ANNOTATION 은 default 값이므로 생략 가능
            includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class)},
            excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)}
    )
     static class ComponentFilterAppConfig{

    }
}
