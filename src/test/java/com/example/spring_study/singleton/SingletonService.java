package com.example.spring_study.singleton;

public class SingletonService {

    // static으로 class 레벨에 올라가기 때문에 객체가 1개만 존재
    private static final SingletonService instance = new SingletonService();

    // public인 getInstance()로 생성되어 있는 Singleton 객체를 불러옴(항상 같은 인스턴스를 불러옴)
    public static SingletonService getInstance() {
        return instance;
    }

    // private 이기 때문에, 외부에서는 Singleton 객체를 생성할 수 없음
    private SingletonService() {

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
