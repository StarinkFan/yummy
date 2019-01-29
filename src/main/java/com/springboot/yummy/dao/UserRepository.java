package com.springboot.yummy.dao;

import com.springboot.yummy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository  extends JpaRepository<User,String> {
    User findFirstByEmailAndPassword(String email, String password);

    User findFirstByEmail(String email);

}
