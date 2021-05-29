package com.atguigu.controller;


import com.atguigu.bean.Book;
import com.atguigu.bean.Cart;
import com.atguigu.bean.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CartController{

    @Autowired
    BookService bookService;


    @RequestMapping("/updateCount")
    protected String updateCount(@RequestParam(value = "id")int id, @RequestParam(value = "count")int count, HttpSession session) throws ServletException, IOException{
        // 获取请求的参数 商品编号 、商品数量

        // 获取Cart购物车对象
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            // 修改商品数量
            cart.updateCount(id,count);
            // 重定向回原来购物车展示页面

        }
        return "/pages/cart/cart";

    }


    @RequestMapping("clear")
    protected String clear(HttpSession session) throws ServletException, IOException{
        // 1 获取购物车对象
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            // 清空购物车
            cart.clear();
            // 重定向回原来购物车展示页面

        }
        return "/pages/cart/cart";
    }

    @RequestMapping("deleteItem")
    protected String deleteItem(@RequestParam(value = "id")int id, HttpSession session) throws ServletException, IOException{
        // 获取商品编号
        // 获取购物车对象
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            // 删除 了购物车商品项
            cart.deleteItem(id);
            // 重定向回原来购物车展示页面

        }
        return "/pages/cart/cart";

    }




    @RequestMapping("addItem")
    protected String addItem(@RequestParam(value = "id")int id,HttpSession session) throws ServletException, IOException {
        // 获取请求的参数 商品编号

        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);

        // 最后一个添加的商品名称
        session.setAttribute("lastName", cartItem.getName());

        return "/pages/cart/cart";
    }
@RequestMapping(name = "ajaxAddItem",produces = {"text/plain;charset=utf-8","text/html;charset=utf-8"})
    protected @ResponseBody String ajaxAddItem(@RequestParam(value = "id")int id, HttpSession session) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        // 调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookById(id);
        // 把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        // 最后一个添加的商品名称
        session.setAttribute("lastName", cartItem.getName());

        //6、返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<String,Object>();

        resultMap.put("totalCount", cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);

    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11ajax");
        return resultMapJsonString;

    }





}
