package com.example.spring_study.singleton;

public class StatefulService {

    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + ", price = " + price);
        this.price = price; // 여기서 문제 발생! -> 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야함(아래 주석 코드와 같이 변경해야함)
    }

    public int getPrice() {
        return price;
    }
}

// price를 필드에서 없애고, return하는 값으로 주는 방식으로 변경
// public class StatefulService {
//
//     public int order(String name, int price) {
//         System.out.println("name = " + name + ", price = " + price);
//         return price;
//     }
// }
