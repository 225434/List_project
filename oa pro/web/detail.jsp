<%@ page import="bean.Dept" %>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看</title>
</head>
<body>
<div>
    <h1></h1>
    <% Dept dept = (Dept) request.getAttribute("Dept");%>
    <p>部门编号：<%=dept.getId()%></p>
    <p>部门名称：<%=dept.getName()%></p>
    <p>部门地址：<%=dept.getLocal()%></p>
    <form action="">
        <input type="button" value="返回" onclick="window.history.back()">
    </form>
</div>
</body>
</html>