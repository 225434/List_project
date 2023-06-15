<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增部门</title>
    <link href="${pageContext.request.contextPath}/css/style-add.css" type="text/css" rel="stylesheet">
</head>
<body>
<div id="c1">
    <form action="<%=request.getContextPath()%>/dept/save" method="post">
        <table>
            <caption>新增部门</caption>
            <tr>
                <td>部门编号：</td>
                <td><label>
                    <input type="text" name="id">
                </label></td>
            </tr>
            <tr>
                <td>部门名称：</td>
                <td><label>
                    <input type="text" name="name">
                </label></td>
            </tr>
            <tr>
                <td>部门位置：</td>
                <td><label>
                    <input type="text" name="local">
                </label></td>
            </tr>
        </table>
        <input id="button" type="submit" value="提交">
    </form>
</div>
</body>
</html>