package com.springboot.yummy.dao;

import com.springboot.yummy.entity.OrderCommodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderCommodityRepository extends JpaRepository<OrderCommodity, String> {
    List<OrderCommodity> findByOid(int oid);
}
