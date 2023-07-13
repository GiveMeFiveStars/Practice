package com.sichuan.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author fsj
 * @date 2023/7/12 21:48
 */
public class BaseDao {
    /**
     * 功能描述
     * 增删改的万能方法
     *
     * @param sql 表示我们传入的sql语句
     * @param obj 表示我们自定义的参数
     * @return int
     */
    public int modifyData(String sql, Object[] obj) {
        Connection conn = ConnUtil.getConn();
        int result = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 功能描述
     * 查询的万能方法
     * @param sql 我们传入的查询sql语句
     * @param obj 我们传入的自定义参数
     * @return java.sql.ResultSet
     */
    public ResultSet getDataByAny(String sql, Object[] obj) {
        Connection conn = ConnUtil.getConn();
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
