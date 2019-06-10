package com.springboot.yummy.service;

import com.springboot.yummy.entity.Discount;

import java.util.Map;

public interface DiscountService {
    Discount[] getDiscounts(int rid);

    int saveDiscount(Map<String, Object> map);

    boolean deleteDiscount(int did);

    boolean validate(int cid);

    boolean invalidate(int cid);

    boolean hasSameDiscount(int rid, int did, int total);
}
