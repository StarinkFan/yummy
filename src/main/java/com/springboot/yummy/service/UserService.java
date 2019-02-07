package com.springboot.yummy.service;


import java.util.Map;

public interface UserService {

    /**
     * 检查用户名密码是否正确
     * @param name 用户名
     * @param password 密码
     * @return 验证通过则将用户信息返回，否则返回null
     */
    Map<String,Object> checkLogin(String name, String password);

    boolean checkIfRegistered(String email);

    void addUser(String name, String password, String email, String phone);

    Map<String,Object> getInfo(int uid);

    void modifyInfo(Map<String, Object> map);

    boolean logoff(int uid);
}
