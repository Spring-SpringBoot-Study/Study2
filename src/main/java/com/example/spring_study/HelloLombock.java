package com.example.spring_study;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombock {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombock helloLombock = new HelloLombock();
        helloLombock.setName("hello"); // Lombock 라이브러리의 @Setter로 자동으로 setName() 가능

        String name = helloLombock.getName(); // Lombock 라이브러리의 @Getter로 자동으로 getName() 가능
        System.out.println("name = " + name);
    }
}
