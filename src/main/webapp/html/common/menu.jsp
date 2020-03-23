<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单</title>
</head>
<script>
    $(function () {
        $.ajax({
            url: "/menu/menu",
            type: "get",
            data: {},
            dataType: "json",
            success: function (data) {
                var parent = data.parent;
                var son = data.son;

                var html = "";
                for (var i = 0; i < parent.length; i++) {
                    html = html + parent[i].name;
                    html = html + "<ul>";
                    for (var j = 0; j < son.length; j++) {
                        if (son[j].pId == parent[i].id) {
                            html = html + '<li><a href="' + son[j].url + '">' + son[j].name + '</a></li>';
                        }
                    }
                    html = html + "</ul>";
                }

                $("#left").append(html);
            }
        });
    });

</script>

<body>

<div id="left">
    <%--系统管理--%>
    <%--<ul>--%>
    <%--<li><a href="/user/listPage">用户管理</a></li>--%>
    <%--<li><a href="/html/user/add.jsp">部门管理</a></li>--%>
    <%--</ul>--%>
</div>

</body>
</html>