package web.action;

import BaseDao.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/user/login", "/user/logout"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doloign(request, response);
        } else if ("/user/logout".equals(servletPath)) {
            dologout(request, response);
        }
    }

    private void doloign(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String loginUsername = request.getParameter("username");
        String loginPassword = request.getParameter("password");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        boolean success = false;

        try {
            connection = DBUtil.getConnection();
            String sql = "select password from userlist where account=? and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginUsername);
            preparedStatement.setString(2, loginPassword);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }

        if (success) {
            //此处session一定不为空
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("username", loginUsername);
            //如果用户选择十天内免登录
            if (request.getParameter("login") != null) {
                String login = request.getParameter("login");
                //实现十天内免登录
                if (login.equals("free")) {
                    //使用两个cookie储存用户账号和密码
                    Cookie cookie_username = new Cookie("username", loginUsername);
                    Cookie cookie_password = new Cookie("password", loginPassword);
                    cookie_username.setMaxAge(60 * 60 * 24 * 10);
                    cookie_password.setMaxAge(60 * 60 * 24 * 10);
                    cookie_username.setPath(request.getContextPath());
                    cookie_password.setPath(request.getContextPath());
                    response.addCookie(cookie_username);
                    response.addCookie(cookie_password);
                }
            }
            //成功，跳转到列表界面
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    public void dologout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            //销毁session
            session.invalidate();
            //手动销毁cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie:cookies ) {
                    String name = cookie.getName();
                    if ("username".equals(name) || "password".equals(name)) {
                        cookie.setMaxAge(0);
                    }
                }
            }
            //跳转到登陆页面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }
}
