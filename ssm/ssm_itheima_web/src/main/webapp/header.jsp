<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/21/021
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <%
        String header = "";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("header".equals(cookie.getName())){
                header= cookie.getValue();
                request.setAttribute("dd",header);
            }
        }
    %>

</head>
<body >
<div class="center-block">
    <img src="${dd}" alt="头像" class="img-circle" style="width: 80px;height: 80px;
    display: block;
    margin-left: auto;
    margin-right: auto; "/>
</div>
<div >
    <h4 align="center">${user.name}:<a href="${pageContext.request.contextPath}/users/exit">退出</a></h4>
</div>
</body>
</html>
