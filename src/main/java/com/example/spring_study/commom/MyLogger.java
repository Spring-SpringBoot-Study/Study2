package com.example.spring_study.commom;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// Proxy로 만들지 않으면, 애플리케이션이 켜질 때, LogDemoController.java, LogDemoSerive.java에서 private final MyLogger myLogger에서 의존성 주입할 때, MyLogger가 Bean에 없다고 나옴
// myLogger는 request 스코프이기 때문에, http 요청할 때만 사용 가능 -> 애플리케이션 실행 지점에는 http 요청이 없는 시점이기 때문에 에러가 나는 것!
// 이것을 proxy로 가짜 MyLogger$$EnhancerBySpringCGLIB 를 만들고, 나중에 http 요청 시에 실제 Bean을 주입함

// 적용 대상이 인터페이스가 아닌 클래스면 TARGET_CLASS를 선택
// 적용 대상이 인터페이스면 INTERFACES를 선택
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] " + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
