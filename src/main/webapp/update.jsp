<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <script src="${path}/static/js/jquery.js"></script>
</head>
<script>
    $(function () {
        $("#btn-email").click(function () {
            var email =$("#email").val();
            var reg=new RegExp("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
            if(!reg.test(email)){
                $("#lable-eamil").text("邮箱格式不正确");
                return false;
            }
            $.ajax({
                url: "/user/sendEmail",
                type: "get",
                data: {email: email},
                dataType: "text",
                success: function (data) {
                    if (data ==200) {
                        $("#lable-eamil").text("已发送验证码至您的邮箱！");
                    } else {
                        $("#lable-eamil").text("邮箱不存在");
                    }
                }
            });

        });
        $("#verification").blur(function () {
            var verification = $("#verification").val();
            $.ajax({
                url: "/user/checkVerification",
                type: "get",
                data: {verification: verification},
                dataType: "text",
                success: function (data) {
                    if (data == 500) {
                        $("#lable-verification").text("验证码错误");
                        $("#verification-flag").val(0);
                    }else {
                        $("#lable-verification").text("验证码正确");
                        $("#verification-flag").val(1);
                    }
                }
            });
        });
        $("#password").blur(function () {
            if($("#password").val().trim()==""){
                $("#lable-password").text("请输入密码");
                $("#password-flag").val(0);
                return false;
            }
            $("#lable-password").text("");
            $("#password-flag").val(1);
        });
        $("#btn-update").click(function () {
            var password=$("#password").val().trim();
            $.ajax({
                url: "/user/updatePassword",
                type: "get",
                data: {password: password},
                dataType: "text",
                success: function (data) {
                    if (data == 200) {
                        alert("修改成功！");
                        location.href="index.jsp";
                    }
                }
            });
        });
    })
</script>
<body>
<%--<form>--%>
    邮箱：<input type="text" name="email" id="email" />
    <button id="btn-email">发送验证码</button>
    <label id="lable-eamil"></label>
    <br/>
    验证码：<input type="text" name="verification" id="verification"/>
        <label id="lable-verification"></label>
    <br/>
    密码：<input type="text" name="password" id="password" />
    <label id="lable-password"></label>
    <br/>
    <input type="button" id="btn-update" value="修改"/>${emailCode}
    <%-- 标识表单是否可以提交 0:不可以 1:可以 --%>
    <input type="hidden" id="verification-flag" value="0">
    <input type="hidden" id="password-flag" value="0">
<%--</form>--%>
</body>
</html>
