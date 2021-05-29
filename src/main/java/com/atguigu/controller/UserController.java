package com.atguigu.controller;

import com.atguigu.bean.User;


import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
@SessionAttributes("user")
public class UserController {

@Autowired
UserService userService;

    @RequestMapping("/login")
    public String login(User user,Model model){

        User loginUser = userService.login(user);
        System.out.println("login");
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息，保存到Request域中
            model.addAttribute("msg", "用户或密码错误！");
            model.addAttribute("username", user.getUsername());
            //   跳回登录页面
            return "/pages/user/login";

        } else {
            // 登录 成功
            // 保存用户登录的信息到Session域中

            model.addAttribute("user", loginUser);
            //跳到成功页面login_success.html
            return "redirect:/pagenull/1";
        }

    }

    @RequestMapping("/regist")
    public String regist(User user,@RequestParam(value = "code")String code, Model model,HttpSession session){



        // 获取Session中的验证码

        String token=(String) session.getAttribute("KAPTCHA_SESSION_KEY");
        // 删除 Session中的验证码
        session.removeAttribute(KAPTCHA_SESSION_KEY);




        System.out.println("regist");
//        2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if (token!=null && token.equalsIgnoreCase(code)) {
//        3、检查 用户名是否可用
            if (userService.existsUsername(user.getUsername())) {
                System.out.println("用户名[" + user.getUsername() + "]已存在!");

                // 把回显信息，保存到Request域中
                model.addAttribute("msg", "用户名已存在！！");
                model.addAttribute("username", user.getUsername());
                model.addAttribute("email", user.getEmail());

//        跳回注册页面
                return "/pages/user/regist";

            } else {
                //      可用
//                调用Sservice保存到数据库
                userService.registUser(user);
//
//        跳到注册成功页面 regist_success.jsp
                return "/pages/user/regist_success";
            }
        } else {
            // 把回显信息，保存到Request域中
            model.addAttribute("msg", "验证码错误！！");
            model.addAttribute("username", user.getUsername());
            model.addAttribute("email", user.getEmail());

            System.out.println("验证码[" + code + "]错误");
            return "/pages/user/regist";
        }

    }
@RequestMapping("logout")
    protected String logout(SessionStatus sessionStatus,HttpSession session) throws ServletException, IOException {

         sessionStatus.setComplete();

//        2、重定向到首页（或登录页面）。
        return "redirect:pagenull/1";
    }



}
