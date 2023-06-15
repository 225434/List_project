<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/style-login.css" type="text/css" rel="stylesheet">
    <title>登录oa系统</title>
    <link rel="short icon" href="${pageContext.request.contextPath}/image/OA.png">
</head>
<body>
    <div id="main">
        <h1 id="title">账 号 登 录</h1>
        <form action="<%=request.getContextPath()%>/user/login" method="post" onsubmit="
        if (this.elements[0].value.length === 0 && this.elements[1].value.length === 0) alert('请输入用户名和密码')
    else if (this.elements[0].value.length === 0) alert('请输入用户名')
    else if (this.elements[1].value.length === 0) alert('请输入密码')
    else if (!document.getElementById('sys').checked) alert('请先勾选同意协议')">
            <label class="label">
                <input class="input" type="text" name="username" placeholder="用户名">
            </label>
            <label class="label">
                <input class="input" type="password" name="password" placeholder="密码">
            </label>
            <input class="button" type="submit" value="登录">
            <label id="non-login">
                <input type="checkbox" name="login" value="free">
                十天内免登录
            </label>
            <label id="policy">
                <input type="checkbox" id="agreement">
                阅读并接受<a href="agreement.jsp">用户协议</a>和<a href="policy.jsp">隐私政策</a>
            </label>
        </form>
    </div>
</body>
</html>