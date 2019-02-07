package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RestaurantRepository  extends JpaRepository<Restaurant,String> {
    Restaurant findFirstByRid(int rid);
    Restaurant findFirstByOwner(int owner);
    Restaurant findFirstByIdCode(String idCode);
    List<Restaurant> findAllByIfValidAndIdCode(boolean ifValid, String idCode);
}
