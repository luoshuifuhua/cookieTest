package com.qingqiao.service.implement;

import com.qingqiao.dao.UserDao;
import com.qingqiao.entity.User;
import com.qingqiao.service.UserService;

import java.util.List;

/**
 * @author H_H
 * @date 2022/08/01 18:45
 **/
public class UserServiceImp implements UserService {
    UserDao userDao= new UserDao();
    @Override
    public List<User> queryAll(Integer page) {
        return userDao.queryAll(page);
    }

    @Override
    public User login(String name, String pasword) {
        return userDao.login(name,pasword);
    }

    @Override
    public int tolPage() {
        return userDao.tolPage();
    }
}
