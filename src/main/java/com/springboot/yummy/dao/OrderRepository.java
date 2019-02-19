package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
    
}
