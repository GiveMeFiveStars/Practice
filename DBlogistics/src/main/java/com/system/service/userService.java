package com.sichuan.service;

/**
 * @author fsj
 * @date 2023/7/12 21:57
 */
public interface userService {
    /**
     * 功能描述
     * 用户登录 这里和UserDao一样,方便我们servlet的调用
     * @param username 传入获取的用户名
     * @param password 传入获取的密码
     * @return boolean
     * @date
     */
    public boolean login(String username, String password);

}
