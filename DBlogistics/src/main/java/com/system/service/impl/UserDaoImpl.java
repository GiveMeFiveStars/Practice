package com.sichuan.dao.impl;

import com.sichuan.dao.userDao;
import com.sichuan.utils.BaseDao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author fsj
 * @date 2023/7/12 21:53
 */
public class UserDaoImpl extends BaseDao implements userDao {
    @Override
    public boolean login(String username, String password) {
        //定义一个查询的sql语句
        String sql = "select * from admin where username=? and password=?";
        /**
         *功能描述
         * 利用super关键字调用BaseDao中getDataByAny方法(也可以用this)
         * @param username 我们的查询依据
         * @param password 我们查询依据
         * @return boolean 返回一个布尔数据类型
         * ResultSet 返回一个结果集
         */
        ResultSet result = super.getDataByAny(sql, new Object[]{username, password});
        //定义一个boolean的变量来确定我们查询是否成功
        boolean flag = false;
        try {
            while (result.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
