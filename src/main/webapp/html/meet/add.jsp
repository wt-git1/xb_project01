<%--
  Created by IntelliJ IDEA.
  User: 黄
  Date: 2020/3/23
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/html/common/head.jsp" %>
<%@include file="/html/common/menu.jsp"%>
<div  id="right">
    <form>
        主题：<input type="text" id="title" name="title"/><br/>
        部门：<select id="dept" name="dept">
        <option>fd1</option>
        <option>fd2</option>
        <option>fd3</option>
    </select><br/>
        人员：<select multiple id="user" name="user">
        <option>fd1</option>
        <option>fd2</option>
        <option>fd3</option>
    </select><br/>
        开始时间<input type="date"><br/>
        结束时间<input type="date"><br/>
        内容<textarea rows="5" cols="100"></textarea>
    </form>
</div>
</body>
</html>
