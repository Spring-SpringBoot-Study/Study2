package com.example.spring_study.spring_study.order;

public interface OrderSevice {
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
