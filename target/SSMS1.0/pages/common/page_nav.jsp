<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/8
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条的开始--%>
<div id="page_nav">
    <%--大于首页，才显示--%>
    <c:if test="${!info.isFirstPage}">
        <a href="${url}/${info.firstPage}">首页</a>
        <a href="${url}/${info.prePage}">上一页</a>
    </c:if>
    <%--页码输出的开始--%>
    <c:forEach items="${info.navigatepageNums}" var="num">
        <c:if test="${num==info.pageNum}">
            【${num}】
        </c:if>
        <c:if test="${num!=info.pageNum}">
            <a href="${url}/${num}">${num}</a>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>


    <%-- 如果已经 是最后一页，则不显示下一页，末页 --%>


    <c:if test="${!info.isLastPage}">
        <a href="${url}/${info.nextPage}">下一页</a>
        <a href="${url}/${info.lastPage}">末页</a>
    </c:if>

    共${ info.pages }页，${ info.total }条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">

        $(function () {
            // 跳到指定的页码
            $("#price").click(function () {


                var min=$("#min").val();
                var max=$("#max").val();

                <%--var pageTotal = ${requestScope.page.pageTotal};--%>
                <%--alert(pageTotal);--%>

                // javaScript语言中提供了一个location地址栏对象
                // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
                // href属性可读，可写
                <%--location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo=" + pageNo;--%>
                location.href ="page/"+min+"/"+max+"/1";

            });

            $("#searchPageBtn").click(function () {

                var pageNo = $("#pn_input").val();

                <%--var pageTotal = ${requestScope.page.pageTotal};--%>
                <%--alert(pageTotal);--%>

                // javaScript语言中提供了一个location地址栏对象
                // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
                // href属性可读，可写
                <%--location.href = "${pageScope.basePath}${ requestScope.page.url }&pageNo=" + pageNo;--%>
                location.href ="${url}/"+pageNo;

            });
        });

    </script>

</div>
<%--分页条的结束--%>


