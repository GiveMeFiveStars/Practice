package com.sichuan.service.impl;

import com.sichuan.dao.impl.UserDaoImpl;
import com.sichuan.dao.userDao;
import com.sichuan.service.userService;

/**
 * @author fsj
 * @date 2023/7/12 21:58
 */
public class userServiceImpl implements userService {

    userDao userDao = new UserDaoImpl();

    @Override
    public boolean login(String username, String password) {
        return userDao.login(username, password);
    }
}
