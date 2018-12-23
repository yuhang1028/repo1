<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/17
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@include file="../../header.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>
</head>

<%
    String username = "";
    String password = "";
    String ck = "";
    Cookie[] cok = request.getCookies();
    String msg = "";
    if(cookies != null){
       for (Cookie cookie : cok) {
           if ("username".equals(cookie.getName())){
               username = cookie.getValue();
               ck = "checked";
           }
           if ("password".equals(cookie.getName())){
               password = cookie.getValue();
           }
       }
   }
    request.setAttribute("username",username);
    request.setAttribute("password",password);
    request.setAttribute("ck",ck);

%>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员登录</h3>
    <form action="${pageContext.request.contextPath}/users/login" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="user">用户名：</label>
            <input type="text" name="username" class="form-control" id="user" value="${username}" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" value="${password}" id="password" placeholder="请输入密码"/>
        </div>
        <div class="form-group">
            记住密码:<input type="checkbox" name="ck"  value="1" ${ck} >
        </div>
        <div >
            <label for="head">头像：</label>
            <input type="file" name="headimg" id="head"/>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录">
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span>
        </button>
        <strong>${msg}</strong>
    </div>
</div>
</body>
</html>