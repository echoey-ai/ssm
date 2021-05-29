package com.atguigu.interceptors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.atguigu.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstHandlerInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user =  (User)httpServletRequest.getSession().getAttribute("user");
        if(user!=null){
            return true;
        } else {

            System.out.println(this.getClass().getName() + " - preHandle");
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(httpServletRequest, httpServletResponse);
            return false;

        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println(this.getClass().getName() + " - postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println(this.getClass().getName() + " - afterCompletion");
    }
}
