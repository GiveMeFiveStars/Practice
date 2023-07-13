package com.sichuan.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author fsj
 * @date 2023/7/12 21:45
 */
public class ConnUtil {
    //定义一个方法来获取数据库的连接
    public static Connection getConn() {
        //初始化数据库连接对象conn
        Connection conn = null;
        //利用try...catch处理异常
        try {
            //注册数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //开始连接自己的数据库
            //test是数据库名称
            //root 一般不需要修改
            //password:修改为自己数据库的密码
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sichuan", "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
