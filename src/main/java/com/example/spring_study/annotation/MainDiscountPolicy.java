package com.example.spring_study.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
// @Qualifier("mainDiscountPolicy")를 포함하고 있는 @MainDiscountPolicy라는 어노태이션 생성
public @interface MainDiscountPolicy {
}
