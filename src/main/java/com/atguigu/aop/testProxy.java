package com.atguigu.aop;

import com.atguigu.bean.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Aspect
public class testProxy {

    //相同切入点抽取
    @Pointcut(value = "execution(* com.atguigu.controller.myfirstcontroller.aoptest(..))")
    public void pointdemo() {
    }

    //前置通知
    //@Before注解表示作为前置通知
    @Before(value = "pointdemo()&&args(a,b,c)",argNames="b,a,c")
    public void before(int b,int a,String c) throws ServletException, IOException {
        System.out.println(b+"+++++++"+a+"+++++++++"+c);

    }

    //后置通知（返回通知）
    @AfterReturning(value = "pointdemo()")
    public void afterReturning() {
        System.out.println("afterReturning.........");
    }

    //最终通知
    @After(value = "pointdemo()")
    public void after() {
        System.out.println("after.........");
    }

    //异常通知
    @AfterThrowing(value = "pointdemo()")
    public void afterThrowing() {
        System.out.println("afterThrowing.........");
    }

    //环绕通知
    @Around(value = "pointdemo()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前.........");

        //被增强的方法执行
        Object obj=proceedingJoinPoint.proceed();

        System.out.println("环绕之后.........");

        return obj;
    }
}
