package com.example.spring_study.scan.filter;

import java.lang.annotation.*;

@Target({ElementType.TYPE}) // 어디에 붙는 어노태이션인지 -> TYPE이면 class에 붙는 어노태이션이라는 뜻
@Retention(RetentionPolicy.RUNTIME)
@Documented
// Component 스캔에서 제외할 것을 지정하는 어노태이션 생성
public @interface MyExcludeComponent {
}
