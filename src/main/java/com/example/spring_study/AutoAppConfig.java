package com.example.spring_study;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 현재 프로젝트의 AppConfig 파일은 수동으로 Bean을 등록하는 예제임
        // 이를 제외해주지 않으면, 충돌이 남(실무에선 거의 x)
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// @Component 라고 붙은 것을 모두 찾아서 자동으로 Spring Bean으로 등록해줌
public class AutoAppConfig {

}
