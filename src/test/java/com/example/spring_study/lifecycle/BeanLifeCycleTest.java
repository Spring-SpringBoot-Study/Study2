package com.example.spring_study.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // 컨테이너가 종료되면서, afterPropertiesSet()가 호출됨
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean // Bean이 완전히 등록된 후에 afterPropertiesSet()이 호출됨
        public NetworkClient networkClient() {
            // 객체의 생성과 초기화는 분리하는 것이 좋다
            // 생성자는 필수 정보(파라미터)를 받고, 메모를 할당해서 객체를 생성하는 책임을 가짐
            // 반면, 초기화는 이렇게 생성된 값들을 활용해서 외부 커넥션을 연결하는 무거운 동작을 수행
            // -> 이러한 무거운 동작을 생성자에서 하는 것보다 이를 분리하는 것이 유지보수 관점에서 좋음
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
