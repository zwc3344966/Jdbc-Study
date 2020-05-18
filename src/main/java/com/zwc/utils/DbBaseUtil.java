package com.zwc.utils;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author: zhangwch
 * @create: 2020-05-18 15:47
 **/
public class DbBaseUtil {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            InputStream in = DbBaseUtil.class.getClassLoader().getResourceAsStream("db.properties");
            Properties prop = new Properties();
            prop.load(in);

            driver = prop.getProperty("driver");
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO:" + e.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            System.out.println("~~~~~~~~~~~~~~~~~~数据库连接~~~~~~~~~~~~~~~~~~~");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("~~~~~~~~~~~~~~~~~~数据库连接成功~~~~~~~~~~~~~~~~~~~");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("~~~~~~~~~~~~~~~~~~数据库连接失败~~~~~~~~~~~~~~~~~~~");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ClassNotFound:" + e.getMessage());
        }
        return connection;
    }

    public static ResultSet executeQuery(Connection conn, PreparedStatement ps, ResultSet rs, String sql, List<Object> para) {
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < para.size(); i++) {
                ps.setObject(i + 1, para.get(i));
            }
            rs = ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 关闭连接
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != ps) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
