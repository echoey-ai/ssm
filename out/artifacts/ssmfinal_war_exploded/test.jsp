<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/5/4
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>


<html>
<head>
    <title>Title123</title>
    <script type="text/javascript">



        $(function () {
            $("#999").click(function(){
                $.ajax(
                    {
                        url:"/ajax",

                        type:"post",

                        success:function(data)
                        {

                            for(var i = 0; i < data.length; i++)
                            {
                                var a=data[i].username+"<br>+a"
                                $(".888").append(a);
                            }
                        }
                    });
                return false ;
            });

        });

    </script>



</head>
<body>
<a href="/ex" >ex</a><br>

<a href="/ajax" id="999">ajax</a><br>
<div id="666" class="888"></div>

<a href="/download">下载</a><br>

<form action="testUpload" method="post" enctype="multipart/form-data">
    文件: <input type="file" name="file"/><br><br>
    描述: <input type="text" name="desc"/><br><br>
    <input type="submit" value="提交"/>
</form>


<a href="/testView">testView</a><br>


<a href="testViewAndViewResolver">i18n</a>
<br><br>
<fmt:message key="i18n.username"/>
<br><br>
<fmt:message key="i18n.password"/>
<br><br>

<!--测试 @SessionAttribute 将数据存放到session域中 -->
<a href="testSessionAttributes">testSessionAttributes</a><br><br>
request user : ${requestScope.user } <br><br>
session user : ${sessionScope.user } <br><br>
request school : ${requestScope.school } <br><br>
session school : ${sessionScope.school } <br><br>


<a href="/testMap2">testMap2</a>
names: ${requestScope.names }
names: ${requestScope.modle }
names: ${requestScope.modelMap }



<!--测试 ModelAndView 作为处理返回结果 -->
<a href="/testModelAndView">testModelAndView</a>
time: ${requestScope.time }


${requestScope.aaa}<br/><br/>
${sessionScope.fff}<br/><br/>

<a href="/testServletAPI">testServletAPI</a><br/><br/>

<form action="/testPOJO" method="POST">
    username: <input type="text" name="username"/><br>
    password: <input type="password" name="password"/><br>
    email: <input type="text" name="email"/><br>
    age: <input type="text" name="age"/><br>
    city: <input type="text" name="address.city"/><br>
    province: <input type="text" name="address.province"/>
    <input type="submit" value="Submit"/>
</form>


<a href="/testCookieValue">testCookieValue</a><br/><br/>

<a href="/testRequestHeader">testRequestHeader</a><br/><br/>

<a href="/testRequestParam?username=atguigu&age=10">testRequestParam</a><br/><br/>


<!-- 实验1 测试 REST风格 GET 请求 -->
<a href="/testRESTGet/1">testREST GET</a><br/><br/>


<!-- 实验2 测试 REST风格 POST 请求 -->
<form action="/testRESTPost" method="POST">
    <input type="submit" value="testRESTPost">
</form>
<!-- 实验3 测试 REST风格 PUT 请求 -->
<form action="/testRESTPut/1" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="testRESTPut">
</form>
<!-- 实验4 测试 REST风格 DELETE 请求 -->
<form action="/testRESTDelete/1" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="testRESTDelete">
</form>



</body>
</html>
