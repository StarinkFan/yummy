package com.springboot.yummy.service;

import java.util.Map;

public interface OrderService {
    boolean placeOrder(Map<String, Object> map);
}