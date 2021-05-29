package com.atguigu.controller;

import com.atguigu.bean.Cart;
import com.atguigu.bean.User;
import com.atguigu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@Controller
public class OderController {

@Autowired
    OrderService orderService;
@RequestMapping("createOrder")
    protected String createOrder(HttpSession session) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) session.getAttribute("cart");
        // 获取Userid
        User loginUser = (User) session.getAttribute("user");

        if (loginUser == null) {

            return "/pages/user/login";
        }

        System.out.println("OrderServlet程序在[" +Thread.currentThread().getName() + "]中");

        Integer userId = loginUser.getId();
//        调用orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);
//        req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        session.setAttribute("orderId",orderId);

        return "/pages/cart/checkout";

    }
}
