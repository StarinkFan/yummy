package com.springboot.yummy.dao;

import com.springboot.yummy.entity.OrderCommodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCommodityRepository extends JpaRepository<OrderCommodity, String> {
}
