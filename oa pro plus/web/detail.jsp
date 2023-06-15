<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查看</title>
    <link rel="short icon" href="${pageContext.request.contextPath}/image/OA.png">
</head>
<body>
<div>
    <h1></h1>
    <p>部门编号：${dept.id}
    </p>
    <p>部门名称：${dept.name}
    </p>
    <p>部门地址：${dept.local}
    </p>
    <input type="button" value="返回" onclick="window.history.back()">
</div>
</body>
</html>