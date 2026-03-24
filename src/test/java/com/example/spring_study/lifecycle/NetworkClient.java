package com.example.spring_study.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {

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

    // 이 방법은 외부 라이브러리에는 적용하지 못함
    @PostConstruct // 생성자 이후에 호출한다는 뜻
    public void init() throws Exception { // 의존관계 주입이 끝나면 호출해 주겠다는 뜻
        // 생성자에서는 진짜 생성만 하고, 초기화하는 과정을 분리함
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 생성 메시지");
    }

    @PreDestroy // 종료 이전에 호출한다는 뜻
    public void close() throws Exception { // Bean이 종료될 때 호출
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
