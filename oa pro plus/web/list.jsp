<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门列表</title>
    <link href="${pageContext.request.contextPath}/css/style-list.css" type="text/css" rel="stylesheet">
    <link rel="short icon" href="${pageContext.request.contextPath}/image/OA.png">
</head>

<body>
<script>
    function del(dno) {
        let ok = window.confirm("删除后无法恢复！确认删除？");
        if (ok) {
            document.location.href = "${pageContext.request.contextPath}/dept/delete?id=" + dno;
        }
    }
</script>

<div id="main">
    <h3>欢迎: ${username} !</h3>
    <a href="${pageContext.request.contextPath}/user/logout">[退出系统]</a>
    <div id="c1">
        <table>
            <caption>部门列表</caption>
            <tr>
                <th>序号</th>
                <th>部门编号</th>
                <th>部门名称</th>
                <th>部门地址</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${DeptList}" varStatus="deptStatus" var="dept">
                <tr>
                    <td>${deptStatus.count}</td>
                    <td>${dept.id}</td>
                    <td>${dept.name}</td>
                    <td>${dept.local}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/dept/detail?symbol=detail&id=${dept.id}">详情</a>
                        <a href="javascript:void(0)" onclick="del(${dept.id})">删除</a>
                        <a href="${pageContext.request.contextPath}/dept/detail?symbol=update&id=${dept.id}">修改</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
    <div id="=c2">
        <form action="${pageContext.request.contextPath}/add.jsp" method="get">
            <input type="submit" value="新增部门">
        </form>
    </div>
</div>

</body>
</html>