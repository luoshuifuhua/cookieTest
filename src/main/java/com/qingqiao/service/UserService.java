package com.qingqiao.service;

import com.qingqiao.entity.User;

import java.util.List;

public interface UserService {
    List<User> queryAll(Integer page);
    User login(String name,String pasword);
    int tolPage();

}
