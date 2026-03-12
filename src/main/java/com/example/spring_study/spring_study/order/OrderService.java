package com.example.spring_study.spring_study.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
