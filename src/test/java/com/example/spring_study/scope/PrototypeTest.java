package com.example.spring_study.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototype bean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); // 해당 시점에서 init() 함수가 실행되어 객체가 생성됨

        System.out.println("find prototype bean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); // 해당 시점에서 init() 함수가 실행되어 객체가 생성됨

        // 두 객체의 참조값이 서로 다름(prototype 이기 때문에, 그때마다 서로 다른 객체를 생성해서 반환)
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close(); // 컨테이너를 close()해도 destroy() 함수가 호출되지 않음 -> singletion의 경우에는 이 시점에 destroy()가 호출됨

        // destroy() 함수 호출이 필요시에 container가 아닌, 사용하는 곳에서 직접 함수를 호출해야함
        prototypeBean1.destroy();
        prototypeBean2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean destroy");
        }
    }
}
