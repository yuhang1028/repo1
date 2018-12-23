<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/17
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../header.jsp"%>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>


    <script>
        function del(id) {
           // 删除单个用户
           if( confirm("确认删除吗？")){
               location.href="${pageContext.request.contextPath}/users/deleteUser/"+id;
           }
        }

        //批量删除
        $(function () {
            //全选
           $("#firstCod").click(function () {
               $(".boxs").prop("checked",this.checked);
           });
           $("#delSelected").click(function () {
               var length = $(".boxs:checked").length;
               if(length){
                   if(confirm("确认删除？")){
                       $.ajax({
                           type:"post",
                           url:"${pageContext.request.contextPath}/users/deleteMore",
                           data:$("#form").serialize(),
                           success:function () {
                              location="${pageContext.request.contextPath}/users/findByPage"
                           },
                           error:function () {
                               alert("Error")
                           }
                       });
                   }
               }else{
                   alert("您还没有选中")
               }
           });
        });
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <form class="form-inline" action="${pageContext.request.contextPath}/users/findByPage" method="post">
        <div class="form-group">
            <label for="exampleInputName2">姓名</label>
            <input type="text" class="form-control" name="user.name"  value="${pages.user.name}" id="exampleInputName2" >
        </div>
        <div class="form-group">
            <label for="exampleInputName3">籍贯</label>
            <input type="text" class="form-control"  name="user.address" value="${pages.user.address}" id="exampleInputName3" >
        </div>
        <div class="form-group">
            <label for="exampleInputEmail2">邮箱</label>
            <input type="email" class="form-control"  name="user.email" value="${pages.user.email}" id="exampleInputEmail2"  >
        </div>
        <button type="submit" class="btn btn-default" >查询</button>

    </form>
    <div style="float: right;margin: 5px;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/jump/addUser">添加联系人</a>

        <a class="btn btn-primary" href="#" id="delSelected">删除选中</a>
    </div>
    <br/>

<form id="form">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox"  id="firstCod"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="user" varStatus="a">

        <tr>
            <td><input type="checkbox" name="uid" value="${user.id}" class="boxs"></td>
            <td>${a.count}</td>
            <td>${user.name}</td>
            <td>${user.gender}</td>
            <td>${user.age}</td>
            <td>${user.address}</td>
            <td>${user.qq}</td>
            <td>${user.email}</td>
            <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/users/findById/${user.id}">修改</a>&nbsp;<a class="btn btn-default btn-sm"  onclick="del(${user.id});">删除</a></td>
        </tr>
        </c:forEach>


       <%-- <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="${pageContext.request.contextPath}/jump/addUser">添加联系人</a></td>
        </tr>--%>
    </table>
</form>
</div>


<nav aria-label="Page navigation">
    <ul class="pagination">
        <li>
            <a href="${pageContext.request.contextPath}/users/findByPage?currentPage=${pb.currentPage-1<1? 1 : pb.currentPage-1}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>


        <c:forEach begin="1" end="${pb.totalPage}" var="i">

        <li ${pb.currentPage == i ? "class='active'" :""}><a href="${pageContext.request.contextPath}/users/findByPage?pageRows=5&currentPage=${i}">${i}</a></li>
        </c:forEach>
        <li>
            <a href="${pageContext.request.contextPath}/users/findByPage?currentPage=${pb.currentPage >= pb.totalPage ? pb.totalPage : pb.currentPage+1}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
        <span style="font-size: 25px;margin-left: 5px;">
                    共 ${pb.totalCount} 条记录，共 ${pb.totalPage}页
                </span>
    </ul>

</nav>
</body>
</html>
