<%--
  Created by IntelliJ IDEA.
  User: 黄
  Date: 2020/3/19
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询部门</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp"%>
<div  id="right">
    <form method="post" action="/dept/list">
        部门：<input type="text" name="name" value="${dept.name}">
        <input type="submit" value="查询" class="btn btn-primary">
    </form>
    <a href="/html/dept/add.jsp" class="btn btn-success">新增</a>
    <table class="table table-bordered">
        <tr>
            <td>编号</td>
            <td>部门</td>
            <td>人数</td>
            <td>操作</td>
        </tr>

        <c:forEach items="${list}" varStatus="status" var="d">
            <tr>
                <td>${status.index+1}</td>
                <td>${d.name}</td>
                <td>${d.count}</td>
                <td>
                    <a href="/dept/toUpdateDept?id=${d.id}&name=${d.name}" class="btn btn-info">修改</a>
                    <a href="/dept/delDept?id=${d.id}" class="btn btn-danger">删除</a>
                </td>
            </tr>
        </c:forEach>

    </table>
    <a href="/dept/list?pageNo=1&name=${dept.name}">首页</a>
    <a href="/dept/list?pageNo=${page.pageNo-1<=0?1:page.pageNo-1}&name=${dept.name}">上一页</a>
    <a href="/dept/list?pageNo=${page.pageNo+1>=page.pageCount?page.pageCount:page.pageNo+1}&name=${dept.name}">下一页</a>
    <a href="/dept/list?pageNo=${page.pageCount}&name=${dept.name}">尾页</a>
    当前页：${page.pageNo},共 ${page.pageCount}页
</div>
</body>
</html>
