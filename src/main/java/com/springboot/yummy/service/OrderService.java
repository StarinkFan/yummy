package com.springboot.yummy.service;

import com.springboot.yummy.entity.OrderDetail;

import java.util.Map;

public interface OrderService {
    int placeOrder(Map<String, Object> map);
    OrderDetail getOrderDetail(int oid);
    void deleteOrder(int oid);
    void setState(int oid, int state);
}
