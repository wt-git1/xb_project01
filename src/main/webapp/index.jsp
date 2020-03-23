<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
</script>
<body>
<form action="/login/login" method="post">
    用户名：<input type="text" name="username" id="username"><br><br>
    密码：<input type="text" name="password" id="password"><br><br>
    验证码：<input type="text" name="picCode" id="picCode"><br><br>
    <img src="/img/getPic" id="img" onclick="getPic()" alt="无法加载"><br/>
    一天内自动登录:<input type="checkbox" name="saveTime" value="1">
    <input type="submit" value="登录"><br/>
    <a href="/update.jsp">重置密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/register.jsp">注册账号</a>
</form>
<br/>
<a href="/weChat/weChatLogin">微信登录</a>
<a href="/login/qqLogin">qq登录</a>
</body>
<script>
    function getPic() {
        document.getElementById("img").src = document.getElementById("img").src + "?nocache=" + new Date().getTime();
    }

</script>
</html>
