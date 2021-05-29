package com.atguigu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdviceHandler {


    /*@ResponseStatus(value= HttpStatus.FORBIDDEN,reason="用户名称和密码不匹配")
    public static class UsernameNotMatchPasswordException extends RuntimeException{}

    @ExceptionHandler(value={java.lang.ArithmeticException.class})
    public ModelAndView handleException(Exception ex){

    System.out.println("出现异常啦:"+ex);
    ModelAndView mv = new ModelAndView("pages/error/error500");
    mv.addObject("exception", ex);

    return mv;
    }

    @ExceptionHandler(value={java.lang.RuntimeException.class})
    public ModelAndView handleException2(Exception ex){

        System.out.println("RuntimeException-出现异常啦:"+ex);
        ModelAndView mv = new ModelAndView("pages/error/error500");
        mv.addObject("exception", ex);

        return mv;
    }*/
}
