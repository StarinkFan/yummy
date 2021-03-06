package com.springboot.yummy.service;

import com.springboot.yummy.entity.Order;
import com.springboot.yummy.entity.OrderDetail;

import java.util.List;
import java.util.Map;

public interface OrderService {
    int placeOrder(Map<String, Object> map);
    OrderDetail getOrderDetail(int oid);
    void deleteOrder(int oid);
    int setState(int oid, int state, int baid);
    List<Order> getUserOrders(int uid);
    List<Order> getRestaurantOrders(int rid);
}
