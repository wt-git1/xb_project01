<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
</head>
<script>
    $(function () {

        $("#detail-img").click(function () {
            // 点击图片时触发文件表单控件
            $("#picFile").click();
        });

        $("#picFile").change(function () {
            // 构造文件上传form
            var formData = new FormData();
            formData.append("iconFile", document.getElementById("picFile").files[0]);

            // 上传图片
            $.ajax({
                url: "/img/uploadHeadImg",
                processData: false,      //默认为true,对请求传递的参数(formData)不做编码处理
                contentType: false,       //不对请求头做处理
                data: formData,
                type: "post",
                dataType: "json",
                async: true,
                success: function (res) {
                    if (res.status == 200) {
                        // 上传成功后置空
                        $("#picFile").val("");
                        // 替换成后台传递过来的新的图片url
                        var pic = res.picUrl;
                        $("#detail-img").attr("src", "/img/getHead?pic=" + pic);
                        $("#head-img").attr("src", "/img/getHead?pic=" + pic);
                    } else {
                        alert("修改头像失败！");
                    }
                }
            });

        });
    });

    $(function () {
        // $.ajax({
        //     url: "/dept/listAll",
        //     type: "get",
        //     data: {},
        //     dataType: "json",
        //     success: function (data) {
        //
        //     }
        // });
    });
</script>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp" %>

<div id="right">
    <form action="/user/updateUser" method="post">

        <%--后台以io流的方式返回图片数据--%>
        <img id="detail-img" src="/img/getHead?pic=${loginUser.pic}" alt="加载中..."
             style="width: 50px;height: 50px;"/><br><br>
        <!-- 真正的头像图片上传表单 -->
        <input type="file" id="picFile" style="display: none;">

        <%--隐藏域--%>
        <input type="hidden" name="id" value="${user.id}">

        真实姓名：<input type="text" name="realName" value="${user.realName}"><br><br>

        所属部门：
        <select id="dept" name="deptId" value="">
            <option>请选择</option>
            <c:forEach items="${dept}" var="dept">
                <option value="${dept.id}"
                        <c:if test="${user.deptId==dept.id}">selected</c:if> >${dept.name}</option>
            </c:forEach>
        </select> <br><br>

        年龄：<input type="text" name="age" value="${user.age}"><br><br>

        性别：<input type="radio" name="gender" value="1"
                  <c:if test="${user.gender==1}">checked</c:if> >男
        <input type="radio" name="gender" value="0"
               <c:if test="${user.gender==0}">checked</c:if> >女<br><br>

        <fmt:parseDate var="registerTimeDate" value="${user.registerTime}"
                       pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
        <fmt:formatDate var="registerTimeStr" value="${registerTimeDate}"
                        pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
        注册时间：<input type="text" value="${registerTimeStr}" disabled><br><br>

        <fmt:parseDate var="loginTimeDate" value="${user.loginTime}"
                       pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
        <fmt:formatDate var="loginTimeStr" value="${loginTimeDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>

        上次登录时间：<input type="text" value="${loginTimeStr}" disabled><br><br>

        是否私密：<input type="checkbox" name="isSecret" value="0"
                    <c:if test="${user.isSecret==0}">checked</c:if> ><br><br>

        <input type="submit" value="保存">
    </form>

    <%--<form id="" action="/img/uploadHeadImg" method="post" enctype="multipart/form-data">--%>
    <%--</form>--%>

</div>


</body>
</html>