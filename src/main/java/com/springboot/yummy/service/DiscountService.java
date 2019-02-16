package com.springboot.yummy.service;

import com.springboot.yummy.entity.Discount;

import java.util.Map;

public interface DiscountService {
    Discount[] getDiscounts(int rid);

    boolean saveDiscount(Map<String, Object> map);

    boolean deleteDiscount(int did);
}
