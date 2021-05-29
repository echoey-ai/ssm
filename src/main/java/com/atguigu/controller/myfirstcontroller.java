package com.atguigu.controller;


import com.atguigu.bean.Book;
import com.atguigu.bean.User;

import com.atguigu.bean.User;
import com.atguigu.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;



@Controller
//@RequestMapping("haha")
@SessionAttributes("user")
//@SessionAttributes(value={"user"},types={String.class})
public class myfirstcontroller {




    @RequestMapping("/aop")
    public String aoptest(@RequestParam(value = "id",defaultValue="1")int id,@RequestParam(value="pn",defaultValue="2")Integer pn,@RequestParam(value="q",defaultValue="aaa")String q)
    {
        System.out.println("aop!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "/test";
    }


    @Autowired
    BookService bookService;
    @Autowired
    BookService book;
    @RequestMapping("/getbook")
    public String getbook(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model){

        //得到分页内容
        PageHelper.startPage(pn,2);

        List<Book>books=bookService.queryBooks();
        //得到分页信息
        PageInfo<Book> info=new PageInfo<>(books,6);

        for (Book itme:books) {
            System.out.println(itme.getName());;
        }
        model.addAttribute("info",info);
        return "test";
    }

    @RequestMapping("/gettwo")
    public String gettwo(){


        System.out.println(bookService.queryBookById(1));
        System.out.println(book.queryBookById(1));


        return "test";
    }

/*    @ResponseStatus(value=HttpStatus.FORBIDDEN,reason="用户名称和密码不匹配")
    public class UsernameNotMatchPasswordException extends RuntimeException{}*/

/*    @ExceptionHandler(value={java.lang.ArithmeticException.class})
    public String handleException(Exception ex){
        System.out.println("出现异常啦"+ex);
        return "pages/error/error500";
    }

    public String handleException(Exception ex,Map<String,Object> map){
        System.out.println("出现异常啦:"+ex);
        map.put("exception",ex);
        return "pages/error/error500";
    }*/



    @RequestMapping("/ex")
    public String ex(){
        System.out.println("ex");
        int a=10/0;
        if(true){
//            throw new ExceptionAdviceHandler.UsernameNotMatchPasswordException();
        }

        return "test";
    }



    @RequestMapping("/ajax")
    public @ResponseBody List<User> ajax(){
        System.out.println("ajax");
        List<User> userList = new ArrayList<User>();
        User user0 = new User();
        user0.setUsername("java");
        User user1 = new User();
        user1.setUsername("javase");
        User user2 = new User();
        user2.setUsername("javaee");
        //对象加入集合
        userList.add(user0);
        userList.add(user1);
        userList.add(user2);
        return userList;
    }



    //下载
    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestHeader("User-Agent") String userAgent) throws IOException {
        // 下载文件的路径
        String path = request.getServletContext().getRealPath("static/script/");
        // 构建File
        File file = new File(path + File.separator + "jquery-1.7.2.js");
        // ok表示http请求中状态码200
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        // 内容长度
        builder.contentLength(file.length());
        // application/octet-stream 二进制数据流（最常见的文件下载）
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        // 使用URLEncoding.decode对文件名进行解码
//     filename = URLEncoder.encode(filename, "UTF-8");
        // 根据浏览器类型，决定处理方式
        if (userAgent.indexOf("MSIE") > 0) {
            builder.header("Content-Disposition", "attachment; filename=" + "jquery-1.7.2.js");
        } else {
            builder.header("Content-Disposition", "attacher; filename*=UTF-8''" + "jquery-1.7.2.js");
        }
        return builder.body(FileUtils.readFileToByteArray(file));
    }


    //上传
    @RequestMapping(value="/testUpload",method=RequestMethod.POST)
    public String testUpload(@RequestParam(value="desc",required=false) String desc, @RequestParam("file") MultipartFile multipartFile) throws IOException{
        System.out.println("desc : "+desc);
        System.out.println("OriginalFilename : "+multipartFile.getOriginalFilename());
        InputStream inputStream = multipartFile.getInputStream();
        System.out.println("inputStream.available() : "+inputStream.available());
        System.out.println("inputStream : "+inputStream);

        return "test"; //增加成功页面: /views/success.jsp
    }




    @RequestMapping("/testView")
    public String testView(){
        System.out.println("testView...");
        return "helloView"; //与视图Bean 对象的id一致
    }




    @RequestMapping("/testViewAndViewResolver")
    public String testViewAndViewResolver(){
        System.out.println("testViewAndViewResolver");
        return "test";//i18n
    }





    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String,Object> map){
        User user = new User();
        user.setId(123);
        user.setUsername("fadf");
        user.setPassword("tomcom");
        user.setEmail("@18188");
        map.put("user", user);
        map.put("school", "qinghua");
//默认是被存放到request 域，如果设置了@SessionAttribute注解，就同时存放到session域中
        return "test";
    }


    //重要的放数据方法
    @RequestMapping("/testMap2")
    public String testMap2(Map<String,Object> map, Model model, ModelMap modelMap){
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("Tom","Jerry","Kite"));
        model.addAttribute("model", "org.springframework.ui.Model");
        modelMap.put("modelMap", "org.springframework.ui.ModelMap");

        System.out.println(map == model);
        System.out.println(map == modelMap);
        System.out.println(model == modelMap);

        System.out.println(map.getClass().getName());
        System.out.println(model.getClass().getName());
        System.out.println(modelMap.getClass().getName());
        return "/test";
    }


    @RequestMapping("/testMap1")
    public String testMap(Map<String,Object> map){ //【重点】
        System.out.println(map.getClass().getName());
//org.springframework.validation.support.BindingAwareModelMap
        map.put("names", Arrays.asList("Tom","Jerry","Kite"));
        return "test";
    }

    //重要的返回数据方法
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        System.out.println("testModelAndView");
        ModelAndView mv = new ModelAndView("/test" );//网址
        mv.addObject("time",new Date().toString()); //实质上存放到request域中
        return mv;
    }


    @RequestMapping("/testServletAPI")
    public void testServletAPI(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("testServletAPI, " + request + ", " + response);
        request.setAttribute("aaa","111");
        session.setAttribute("fff","666");
        response.getWriter().write("hello world");
//        out.write("hello springmvc");
//        return "/test";
    }


    @RequestMapping("/testPOJO")
    public String testPojo(User user) {
        System.out.println("testPojo: " + user);
        return "/test";
    }


    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println("testCookieValue: sessionId: " + sessionId);
        return "/test";
    }


    @RequestMapping(value="/testRequestParam")
    public String testRequestParam( @RequestParam(value="username") String username,@RequestParam(value="age",required=false,defaultValue="0") int age)
    {
        System.out.println("testRequestParam - username="+username +",age="+age);
        return "/test";
    }
    @RequestMapping(value="/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value="Accept-Language") String al){
        System.out.println("testRequestHeader - Accept-Language："+al);
        return "/test";
    }



    @RequestMapping(value="/haha")
    public String ssm(){
        System.out.println("666");

        return "/test";
    }
//    查
    @RequestMapping(value="/testRESTGet/{id}",method=RequestMethod.GET)
    public String testRESTGet(@PathVariable(value="id") Integer id){
        System.out.println("testRESTGet id="+id);
        return "/test";
    }
//增加
    @RequestMapping(value="/testRESTPost",method=RequestMethod.POST)
    public String testRESTPost(){
        System.out.println("testRESTPost");
        return "/test";
    }
//改
    @RequestMapping(value="/testRESTPut/{id}",method=RequestMethod.PUT)
    public String testRESTPut(@PathVariable("id") Integer id){
        System.out.println("testRESTPut id="+id);
        return "redirect:/haha";
    }
//删除
    @RequestMapping(value="/testRESTDelete/{id}",method=RequestMethod.DELETE)
    public String testRESTDelete(@PathVariable("id") Integer id){
        System.out.println("testRESTDelete id="+id);
        return "redirect:/haha";
    }




}
