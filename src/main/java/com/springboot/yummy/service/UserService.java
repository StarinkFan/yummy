package com.springboot.yummy.service;

import com.springboot.yummy.entity.User;

public interface UserService {
    int addUser(User user);

    /**
     * 检查用户名密码是否正确
     * @param name 用户名
     * @param password 密码
     * @return 验证通过则将用户信息返回，否则返回null
     */
    int checkLogin(String name,String password);
}
