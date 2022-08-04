<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2022/8/1
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<script src="<%= request.getContextPath()%>/js/jQuery-v3.6.0.js"></script>
<script>
    /*$(function () {

        $.ajax(
            URL:
        )
    })*/

    $(function () {
        setInterval('getDate()',1000);
    })

    //首页
    function firstP() {
        window.location="user?m=list&pageNo=1";
    }
    //下一页
    function nextP() {
        let page = ${sessionScope.pageNo};
        if(page<${sessionScope.lastPage}){
            window.location="user?m=list&pageNo=${sessionScope.pageNo+1}";
        }
    }
    //上一页
    function prevP(){
        let page = ${sessionScope.pageNo};
        if(page!==1){
            window.location="user?m=list&pageNo=${sessionScope.pageNo-1}";
        }
    }
    //尾页
    function lastP() {
        window.location="user?m=list&pageNo=${sessionScope.lastPage}";
    }



    function getDate(){
        let now = new Date();
        let dat = now.toLocaleString();
        $("#now").text(dat)
    }
</script>

<head>
    <title>首页</title>
</head>
<body>
欢迎${sessionScope.login_info.username}登录,时间为 <span id="now"></span>
<br>
<table>
    <tr><th><input type="checkbox" class="cks"></th>
        <th>编号</th>
        <th>姓名</th>
        <th>密码</th>
        <th>操作 | <input type="button" value="添加" id="add"></th>
    </tr>
    <c:forEach items="${list}" var="user">
        <tr>
            <td><input type="checkbox" class="cks" id="${user.id}"></td>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>
                <input type="button" value="删除" class="delete">
                <input type="hidden" value="${user.id}">
                <input type="button" value="修改" class="update">
            </td>
        </tr>

    </c:forEach>
    <%--<tr>
        <td colspan="10">
            <input type="button" value="全选" id="selectAll">
            <input type="button" value="全不选" id="selectNone">
            <input type="button" value="反选" id="fan">
            <input type="button" value="批量删除" id="plsc">
        </td>
    </tr>--%>
    <tr>
        <td colspan="10">
            <input type="button" value="首頁" id="firstPage" onclick="firstP()">
            <input type="button" value="上一页" id="prevPage" onclick="prevP()">
            <input type="button" value="下一页" id="nextPage" onclick="nextP()" >
            <input type="button" value="尾页" id="lastPage" onclick="lastP()">
        </td>
    </tr>

</table>
</body>
</html>
