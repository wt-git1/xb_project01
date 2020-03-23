<%--
  Created by IntelliJ IDEA.
  User: 黄
  Date: 2020/3/19
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改部门</title>
</head>
<script>
    $(function () {
        $("#name").blur(function () {
            var name=$("#name").val();
            $.ajax({
                url:"/dept/checkName",
                type:"get",
                data:{name:name},
                dataType:"text",
                success:function (data) {
                    if (data == 500) {
                        $("#name-label").text("该部门已存在！");
                        $("#name-flag").val(0);
                    } else if($("#name").val().trim()==""){
                        $("#name-label").text("部门不能为空！");
                        $("#name-flag").val(0);
                    }else {
                        $("#name-label").text("✔");
                        $("#name-flag").val(1);
                    }
                }
            })
        });
        $("#btn").click(function () {
            if ($("#name-flag").val() == 0) {
                return false;
            } else {
                $("#form").submit();
            }
        });
        $("#home").click(function () {
            history.go(-1);
        });
    })
</script>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp"%>
<div id="right">
    <form id="form" action="/dept/updateDept" method="post">

        <input type="hidden" name="id" value="${dept.id}"/>
        部门：<input type="text" name="name" value="${dept.name}" id="name"><label id="name-label"></label><br><br>

        <input type="button" id="btn" class="btn btn-info" value="修改">
        <input type="button" value="返回" class="btn btn-dark" id="home">
        <%-- 标识表单是否可以提交 0:不可以 1:可以 --%>
        <input type="hidden" id="name-flag" value="0">
    </form>
</div>
</body>
</html>
