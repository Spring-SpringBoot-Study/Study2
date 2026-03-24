package com.example.spring_study.web;

import com.example.spring_study.commom.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // private final ObjectProvider<MyLogger> myLoggerProvider; // myLogger를 찾을 수 있는 dependacny lookup을 할 수 있는 provider가 주입됨
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) { // HttpServletRequest으로 고객 요청 정보를 받을 수 있음

        String requestURL = request.getRequestURL().toString();
        // MyLogger myLogger = myLoggerProvider.getObject(); // 이 시점에 myLogger가 생성됨 - request 스코프 이므로 HTTP 요청 하나가 들어올 때 딱 한 번 생성됨
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
