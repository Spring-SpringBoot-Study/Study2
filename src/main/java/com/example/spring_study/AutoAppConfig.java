package com.example.spring_study; // AutoAppConfig의 pacakge

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 현재 프로젝트의 AppConfig 파일은 수동으로 Bean을 등록하는 예제임
        // 이를 제외해주지 않으면, 충돌이 남(실무에선 거의 x)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

        // packages를 지정해서 ComponentScan 대상이 되는 영역을 지정할 수 있음
        // basePackages = "com.example.spring_study.member",

        // AutoAppConfig 클래스가 있는 파일의 package를 대상으로 ComponentScan을 진행(여기에선 com.example.spring_study임)
        // basePackageClasses = AutoAppConfig.class

        // 위의 것들을 지정 안하면, @ComponentScan이 붙은 설정 정보 package가 시작 위치가 됨(해당 package 및 하위 폴더 전체에서 Scan)
)
// @Component 라고 붙은 것을 모두 찾아서 자동으로 Spring Bean으로 등록해줌
public class AutoAppConfig {

}
