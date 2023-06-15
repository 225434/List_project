package web.action;

import BaseDao.DBUtil;
import bean.Dept;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "DeptServlet", value = {"/dept/list", "/dept/detail", "/dept/delete", "/dept/save", "/dept/update"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //获取登录状态
        HttpSession session = request.getSession(false);
        //获取请求路径
        String servletPath = request.getServletPath();
        if (session != null && session.getAttribute("username") != null) {
            if ("/dept/list".equals(servletPath)) {
                doList(request, response);
            } else if ("/dept/detail".equals(servletPath)) {
                doDetail(request, response);
            } else if ("/dept/delete".equals(servletPath)) {
                doDel(request, response);
            } else if ("/dept/save".equals(servletPath)) {
                dosave(request, response);
            } else if ("/dept/update".equals(servletPath)) {
                doupdate(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }*/

        //获取请求路径
        String servletPath = request.getServletPath();
        if ("/dept/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(request, response);
        } else if ("/dept/save".equals(servletPath)) {
            dosave(request, response);
        } else if ("/dept/update".equals(servletPath)) {
            doupdate(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Dept> deptList = new ArrayList<>();

        try {
            connection = DBUtil.getConnection();
            String sql = "select * from message";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String local = resultSet.getString("local");

                Dept dept = new Dept();
                dept.setId(id);
                dept.setName(name);
                dept.setLocal(local);

                deptList.add(dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        request.setAttribute("DeptList", deptList);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }


    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Dept dept = new Dept();

        try {
            connection = DBUtil.getConnection();
            String key = request.getParameter("id");
            String sql = "select * from message where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String local = resultSet.getString("local");

                dept.setId(id);
                dept.setName(name);
                dept.setLocal(local);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement, resultSet);
        }
        request.setAttribute("dept", dept);
        request.getRequestDispatcher("/" + request.getParameter("symbol") + ".jsp").forward(request, response);
    }

    public void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int flag = 0;

        try {
            connection = DBUtil.getConnection();
            String key = request.getParameter("id");
            String sql = "delete from message where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key);
            flag = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
        if (flag == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }

    public void dosave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int flag = 0;

        try {
            connection = DBUtil.getConnection();
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String local = request.getParameter("local");
            String sql = "insert into message(id,name,local) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, local);
            flag = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
        if (flag == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }

    public void doupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int flag = 0;

        try {
            connection = DBUtil.getConnection();
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String local = request.getParameter("local");
            String sql = "update message set name=?,local=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, local);
            preparedStatement.setString(3, id);
            flag = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection, preparedStatement);
        }
        if (flag == 1) {
            response.sendRedirect(request.getContextPath() + "/dept/list");
        }
    }
}
