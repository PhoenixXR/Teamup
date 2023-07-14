package com.example.Teamup_web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import static com.sun.webkit.perf.WCFontPerfLogger.log;
/**
 * @author phoeniX.R
 */
public class DBUtils {
/*
连接数据库
jdk8->jdk11/jdk20
服务器tomcat是8.0，所以jakarta全改javax，开发和部署应该一样
mysql是8.0
用户名为        Root
密码是 Xr170014/1001
上面本地测试服务器ip
下面云服务器ip
 */
    private static Connection connection = null;
    public static Connection getConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/hunt?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            String username = "Root";
            String password = "1001";
            connection = DriverManager.getConnection(url,username,password);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    public static void closeConnection()
    {
        if(connection != null)
        {
            try {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}

