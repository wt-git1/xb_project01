<%--
  Created by IntelliJ IDEA.
  User: 黄
  Date: 2020/3/18
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    #top {
        height: 11%;
        border: 1px solid red;
    }

    #left {
        width: 10%;
        height: 90%;
        border: 1px solid red;
        float: left
    }

    #right {
        width: 90%;
        height: 90%;
        border: 1px solid red;
        float: right;
    }
</style>
<script>
    $(function () {
        $("#head-img").click(function () {
            // 点击头像跳转到用户详情页面
            window.location.href = "/user/detail?id=" + $("#loginUserId").val();
        });
    });
</script>
<body>
<div id="top">
    <div>
        <%--后台以io流的方式返回图片数据--%>
        <img id="head-img" src="/img/getHead?pic=${sessionLoginUser.pic}" style="width: 50px;height: 50px;"/>
    </div>
    欢迎：${sessionLoginUser.realName}
    <input id="loginUserId" type="hidden" value="${sessionLoginUser.id}">
    <a href="/login/logOut">退出</a>
</div>
</body>
</html>
