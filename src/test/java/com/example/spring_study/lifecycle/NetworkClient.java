package com.example.spring_study.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스를 시작할 때 호출하는 메서드
    public void connect() {
        System.out.println("connect: " + url);
    }

    // connect가 된 상태에서 call을 부를 수 있다고 가정
    public void call(String message){
        System.out.println("call: " + url + ", message: " + message);
    }

    // 서비스 종료시 호출하는 메서드
    public void disconnect() {
        System.out.println("close: " + url);
    }

    @Override
    // InitializingBean 부모에서 상속
    public void afterPropertiesSet() throws Exception { // 의존관계 주입이 끝나면 호출해 주겠다는 뜻
        // 생성자에서는 진짜 생성만 하고, 초기화하는 과정을 분리함
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 생성 메시지");
    }

    @Override
    // DisposableBean 부모에서 상속
    public void destroy() throws Exception { // Bean이 종료될 때 호출
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
