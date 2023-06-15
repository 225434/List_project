package web.action;

import BaseDao.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/welcome"})
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String servletPath = request.getServletPath();
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    username = cookie.getValue();
                }
                if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }

        //使用username和password
        if (username != null && password != null) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            boolean success = false;

            try {
                connection = DBUtil.getConnection();
                String sql = "select password from userlist where account=? and password=?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
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
                httpSession.setAttribute("username", username);
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
