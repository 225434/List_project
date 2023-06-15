<%@ page import="bean.Dept" %>
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改信息</title>
    <link href="${pageContext.request.contextPath}/css/style-update.css" type="text/css" rel="stylesheet">
</head>
<body>
<div id="c1">
    <form action="<%=request.getContextPath()%>/dept/update" method="post">
        <table>
            <caption>修改信息</caption>
            <tr>
                <% Dept dept = (Dept) request.getAttribute("Dept");%>
                <td>部门编号：</td>
                <td><label>
                    <input type="text" name="id" value="<%=dept.getId()%>" readonly >
                </label></td>
            </tr>
            <tr>
                <td>部门名称：</td>
                <td><label>
                    <input type="text" name="name" value="<%=dept.getName()%>">
                </label></td>
            </tr>
            <tr>
                <td>部门位置：</td>
                <td><label>
                    <input type="text" name="local" value="<%=dept.getLocal()%>">
                </label></td>
            </tr>
        </table>
        <input id="button" type="submit" value="修改">
    </form>
</div>
</body>
</html>