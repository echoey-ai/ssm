package com.atguigu.service.impl;

import com.atguigu.bean.*;
import com.atguigu.dao.BookMapper;
import com.atguigu.dao.OrderItemMapper;
import com.atguigu.dao.OrderMapper;
import com.atguigu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    BookMapper bookMapper;
    @Override

    public String createOrder(Cart cart, Integer userId) {
        System.out.println(" OrderServiceImpl 程序在[" +Thread.currentThread().getName() + "]中");

        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        // 创建一个订单对象
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCreateTime(new Date());
        order.setPrice(cart.getTotalPrice());
        order.setUserId(userId);
        // 保存订单

        orderMapper.insert(order);


        int i = 12 / 0;

        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem();
            orderItem.setId(null);
            orderItem.setName(cartItem.getName());
            orderItem.setCount(cartItem.getCount());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setTotalPrice(cartItem.getTotalPrice());
            orderItem.setOrderId(orderId);
            // 保存订单项到数据库
            orderItemMapper.insert(orderItem);

            // 更新库存和销量
            Book book = bookMapper.selectByPrimaryKey(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getStock() - cartItem.getCount() );
            bookMapper.updateByPrimaryKey(book);

        }
        // 清空购物车
        cart.clear();

        return orderId;
    }
}
