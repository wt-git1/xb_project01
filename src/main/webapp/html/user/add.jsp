<%--
  Created by IntelliJ IDEA.
  User: 黄
  Date: 2020/3/18
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增用户</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp"%>
<div style="height: 80%;width: 89%;border: 1px solid red;float: right;">
    <form id="form" action="/user/register" method="post">
        用户名：<input type="text" value="" name="username" id="username"><label id="username-label"></label><br><br>
        密码：<input type="text" value="" name="password" id="password"><label id="password-label"></label><br><br>
        邮箱：<input type="text" value="" name="email" id="email"><label id="email-label"></label><br><br>
        确认密码：<input type="text" value="" name="rePassword" id="rePassword"><br><br>
        <input type="button" id="btn" value="注册">

        <%-- 标识表单是否可以提交 0:不可以 1:可以 --%>
        <input type="hidden" id="name-flag" value="0">
        <input type="hidden" id="email-flag" value="0">
        <input type="hidden" id="password-flag" value="0">

    </form>
</div>
</body>
</html>
