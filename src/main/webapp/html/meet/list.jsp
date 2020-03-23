<%--
  Created by IntelliJ IDEA.
  User: 黄
  Date: 2020/3/22
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会议</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp"%>
<div id="right">
    <h3 >会议管理</h3>
    <hr/>
    <form method="post" action="/meet/list">
        标题：<input type="text" name="title" value="${meet.title}">
        <select name="status">
            <option value="-1">--请选择--</option>
            <option value="0" <c:if test="${meet.status==0}"> selected</c:if>>未开始</option>
            <option value="1" <c:if test="${meet.status==1}"> selected</c:if>>进行中</option>
            <option value="2" <c:if test="${meet.status==2}"> selected</c:if>>已结束</option>
        </select>
        <input type="submit" value="查询" class="btn btn-primary">
    </form>
    <a href="/html/meet/add.jsp" class="btn btn-success">发布会议</a>${meet.status}${meet.title}
    <c:forEach items="${list}" var="m">
        <div style="margin:10px; border: 1px dashed red">
            <p style="color: red;font-size: 20px">${m.title}
                <b style="font-size: 15px">
                    <c:choose>
                        <c:when test="${m.status==0}">(未开始)</c:when>
                        <c:when test="${m.status==1}">(进行中)</c:when>
                        <c:when test="${m.status==2}">(已结束)</c:when>
                    </c:choose>
                </b>
            </p>
            <p>部门：${m.deptName}</p>
            <p>开始时间：${m.startTime}</p>
            <p>${m.content}</p>
        </div>
    </c:forEach>
<%--    <div style="margin:10px; border: 1px dashed red">--%>
<%--        <p style="color: red;font-size: 20px">研发部开会<b style="font-size: 15px">（未开始）</b></p>--%>
<%--        <p>部门</p>--%>
<%--        <p>开始时间</p>--%>
<%--        <p>讨论加姓问题</p>--%>
<%--    </div>--%>
<%--    <div style="margin:10px; border: 1px dashed red">--%>
<%--        <p style="color: red;font-size: 20px">研发部开会</p>--%>
<%--        <p>部门</p>--%>
<%--        <p>开始时间</p>--%>
<%--        <p>讨论加姓问题</p>--%>
<%--    </div>--%>
    <a href="/meet/list?pageNo=1&title=${meet.title}&status=${meet.status}">首页</a>
    <a href="/meet/list?pageNo=${page.pageNo-1<=0?1:page.pageNo-1}&title=${meet.title}&status=${meet.status}">上一页</a>
    <a href="/meet/list?pageNo=${page.pageNo+1>=page.pageCount?page.pageCount:page.pageNo+1}&title=${meet.title}&status=${meet.status}">下一页</a>
    <a href="/meet/list?pageNo=${page.pageCount}&title=${meet.title}&status=${meet.status}">尾页</a>
    当前页：${page.pageNo},共 ${page.pageCount}页
</div>
</body>
</html>
