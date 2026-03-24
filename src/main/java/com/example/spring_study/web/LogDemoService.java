package com.example.spring_study.web;

import com.example.spring_study.commom.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider; // myLogger를 찾을 수 있는 dependacny lookup을 할 수 있는 provider가 주입됨

    public void logic(String id) {

        MyLogger myLogger = myLoggerProvider.getObject(); // 이 시점에 myLogger가 생성됨 - request 스코프 이므로 HTTP 요청 하나가 들어올 때 딱 한 번 생성됨
        // 이 request 스코프의 myLogger는 하나의 클라이언트(http 요청)당 1개이기 때문에 한 http 요청에서의 Serivce랑 Controller가 공유함(Controller에서 만든 그 myLogger를 가져옴)
        myLogger.log("service id = " + id);
    }
}
