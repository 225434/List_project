package BaseDao;

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("resources.jdbc");
    private static final String driver = bundle.getString("driver");
    private static final String url = bundle.getString("url");
    private static final String username = bundle.getString("username");
    private static final String password = bundle.getString("password");

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection () throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public static void close (Connection connection,PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close (Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
