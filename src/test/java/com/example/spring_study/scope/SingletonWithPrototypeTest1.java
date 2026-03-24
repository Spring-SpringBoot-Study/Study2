package com.example.spring_study.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// import javax.inject.Provider;
import jakarta.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1); // provider를 이용해서 prototype을 DL 의존관계 탐색으로 찾았으므로 1이 나옴(새로운 prototype 생성)
    }

    @Scope("singleton")
    @Component
    static class ClientBean{
        // private final PrototypeBean prototypeBean; // 생성 시점에 prototypeBean이 singleton인 ClientBean에 주입됨 -> 이후에는 계속 같은 prototpe을 singleton에서 사용

        // @Autowired
        // private ObjectProvider<PrototypeBean> prototypeBeanProvider; // Spring이 자동으로 알아서 찾아서 등록해줌

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        // @Autowired
        // public ClientBean(PrototypeBean prototypeBean){
        //     this.prototypeBean = prototypeBean;
        // }

        public int logic(){
            // PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // -> ObjectProvider<PrototypeBean> 사용법
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); // Provider<PrototypeBean> 사용법
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    @Component
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        // prototype Bean이므로 실제로는 호출될 일이 없음
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
