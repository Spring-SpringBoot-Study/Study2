package com.example.spring_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @SpringBootApplication 안에 @ComponentScan가 포함되어 있음
public class SpringStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudyApplication.class, args);
	}

}
