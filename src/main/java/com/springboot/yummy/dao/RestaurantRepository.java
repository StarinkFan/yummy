package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RestaurantRepository  extends JpaRepository<Restaurant,String> {
    Restaurant findFirstByOwner(int owner);
    Restaurant findFirstByIdCode(String idCode);
}
