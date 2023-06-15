<%@page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新增部门</title>
    <link href="${pageContext.request.contextPath}/css/style-add.css" type="text/css" rel="stylesheet">
    <link rel="short icon" href="${pageContext.request.contextPath}/image/OA.png">
</head>
<body>
<div>
    <div id="c1">
        <form action="${pageContext.request.contextPath}/dept/save" method="post">
            <table>
                <caption><h2>新增部门</h2></caption>
                <tr>
                    <td><label>
                        <input class="input" type="text" name="id" placeholder="部门编号">
                    </label></td>
                </tr>
                <tr>
                    <td><label>
                        <input class="input" type="text" name="name" placeholder="部门名称">
                    </label></td>
                </tr>
                <tr>
                    <td><label>
                        <input class="input" type="text" name="local" placeholder="部门位置">
                    </label></td>
                </tr>
            </table>
            <input id="button" type="submit" value="提交">
        </form>
    </div>
</div>

</body>
</html>