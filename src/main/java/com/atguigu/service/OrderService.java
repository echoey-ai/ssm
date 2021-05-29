package com.atguigu.service;

import com.atguigu.bean.Cart;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
