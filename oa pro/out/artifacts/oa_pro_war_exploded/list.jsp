<%@ page import="java.util.List" %>
<%@ page import="bean.Dept" %>
<%@ page contentType="text/html;charset=UTF-8"%>

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
        var ok = window.confirm("删除后无法恢复！确认删除？");
        if (ok) {
            document.location.href = "<%=request.getContextPath()%>/dept/delete?id=" + dno;
        }
    }
</script>
<div id="main">
    <h3>欢迎: <%=session.getAttribute("username")%> !</h3>
    <a href="<%=request.getContextPath()%>/user/logout">[退出系统]</a>
    <div id="c1">
        <table>
            <caption>部门列表</caption>
            <tr >
                <th>序号</th>
                <th>部门编号</th>
                <th>部门名称</th>
                <th>部门地址</th>
                <th>操作</th>
            </tr>

            <%
                List<Dept> deptList = (List<Dept>) request.getAttribute("DeptList");
                int i = 0;
                for (Dept dept :deptList) {
            %>

            <tr >
                <td><%=(++i)%></td>
                <td><%=dept.getId()%></td>
                <td><%=dept.getName()%></td>
                <td><%=dept.getLocal()%></td>
                <td>
                    <a href="<%= request.getContextPath()%>/dept/detail?symbol=detail&id=<%=dept.getId()%>">详情</a>
                    <a href="javascript:void(0)" onclick="del(<%=dept.getId()%>)">删除</a>
                    <a href="<%= request.getContextPath()%>/dept/detail?symbol=update&id=<%=dept.getId()%>">修改</a>
                </td>
            </tr>

            <%
                }
            %>

        </table>
    </div>
    <div id="=c2">
        <form action="<%= request.getContextPath()%>/add.jsp" method="get">
            <input type="submit" value="新增部门">
        </form>
    </div>
</div>

</body>
</html>