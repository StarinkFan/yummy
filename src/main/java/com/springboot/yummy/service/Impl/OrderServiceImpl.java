package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.OrderRepository;
import com.springboot.yummy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public boolean placeOrder(Map<String, Object> map) {
        return false;
    }
}
