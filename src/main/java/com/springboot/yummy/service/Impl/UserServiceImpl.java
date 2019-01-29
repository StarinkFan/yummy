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
    public int checkLogin(String email, String password) {
        User user=userRepository.findFirstByEmailAndPassword(email, password);
        if(user!=null&&(!user.getIfDelete())){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public boolean checkIfRegistered(String email) {
        User user=userRepository.findFirstByEmail(email);
        if(user!=null&&(!user.getIfDelete())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void addUser(String name, String password, String email, String phone) {
        User user=new User(name, password, email, phone, 0, 0.00, false);
        userRepository.save(user);
    }

}
