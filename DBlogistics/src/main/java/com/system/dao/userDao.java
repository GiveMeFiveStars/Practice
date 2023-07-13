package com.sichuan.dao;

/**
 * @author fsj
 * @date 2023/7/12 21:52
 */
public interface userDao {
    /**
     * 功能描述
     * 用户登录
     * @param username 传入获取的用户名
     * @param password 传入获取的密码
     * @return boolean
     * @date
     */
    public boolean login(String username, String password);
}
