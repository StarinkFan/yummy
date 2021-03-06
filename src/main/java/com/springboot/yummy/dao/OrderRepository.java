package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    Order findFirstByOid(int oid);

    void deleteByOid(int oid);

    List<Order> findByUid(int uid);

    List<Order> findByRid(int rid);

    List<Order> findByUidAndState(int uid, int state);

    List<Order> findByRidAndState(int rid, int state);
}
