package action;

import BaseDao.DBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "DeptLestServlet", value = "/DeptLestServlet")
public class DeptLestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应内容类型和字符集
        response.setContentType("text/html;charset=UTF-8");
        //
        PrintWriter printWriter = response.getWriter();
        //输出HTML文件
        printWriter.print("<!DOCTYPE html>");
        printWriter.print("<html lang='en'>");
        printWriter.print("<head>");
        printWriter.print("    <meta charset='utf-8'>");
        printWriter.print("    <title>部门列表</title>");
        printWriter.print("    <link href='style-list.css' type='text/css' rel='stylesheet'>");
        printWriter.print("</head>");
        printWriter.print("<body>");
        printWriter.print("    <div id='c1'>");
        printWriter.print("        <table >");
        printWriter.print("            <caption>部门列表</caption>");
        printWriter.print("            <tr >");
        printWriter.print("                <th>序号</th>");
        printWriter.print("                <th>部门编号</th>");
        printWriter.print("                <th>部门名称</th>");
        printWriter.print("                <th>操作</th>");
        printWriter.print("            </tr>");
        //向数据库查询所有信息
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select * from message";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String local = resultSet.getString("local");
                //
                printWriter.print("            <tr >");
                printWriter.print("                <th>"+ (++i) +"</th>");
                printWriter.print("                <th>" + id +"</th>");
                printWriter.print("                <th>"+ name +"</th>");
                printWriter.print("                <th>"+ local +"</th>");
                printWriter.print("            </tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
        //
        printWriter.print("        </table>");
        printWriter.print("    </div>");
        printWriter.print("    <div id='=c2'>");
        printWriter.print("        <form action='../add/add.html' method='get'>");
        printWriter.print("            <input type='submit' value='新增部门'>");
        printWriter.print("        </form>");
        printWriter.print("    </div>");
        printWriter.print("</body>");
        printWriter.print("</html>");
    }
}
