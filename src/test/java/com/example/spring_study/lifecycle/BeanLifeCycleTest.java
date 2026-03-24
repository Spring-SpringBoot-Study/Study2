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
        ac.close(); // 컨테이너가 종료되면서, close()가 호출됨
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean(initMethod = "init", destroyMethod = "close") // NetWorkClient의 메소드 중에서 초기화와 종료 메소드를 지정
        // -> 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있음
        // Bean이 완전히 등록된 후에 "init()이 호출됨

        // destroyMethod는 (inferred) 추론으로 등록되어 있어서, 종료 메서드는 따로 적어주지 않아도 @Bean으로 등록할 시에는 'close`, 'shutdown' 라는 이름의 메서드를 자동으로 호출해줌
        // -> 즉, 이름 그대로 종료 메서드를 추론해서 호출(일반적으로 외부라이브러리의 경우, close, shudown을 종료 메서드 이름임)
        // -> 이 추론 기능을 사용하기 싫으면 destroyMethod = ""로 지정하면 됨
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
