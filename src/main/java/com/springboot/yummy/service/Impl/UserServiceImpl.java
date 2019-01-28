package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.UserRepository;
import com.springboot.yummy.entity.User;
import com.springboot.yummy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int checkLogin(String email, String password) {
        User user=userRepository.findFirstByEmailAndPassword(email, password);
        if(user!=null&&(!user.getIfDelete())){
            return 1;
        }else{
            return 0;
        }
    }

}
