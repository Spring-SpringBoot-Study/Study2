package com.example.spring_study.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 생성 메시지");

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
}
