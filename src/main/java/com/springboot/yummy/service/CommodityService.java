package com.springboot.yummy.service;

import com.springboot.yummy.entity.Commodity;

import java.util.Map;


public interface CommodityService {
    Commodity[] getCommodities(int rid);

    int saveCommodity(Map<String, Object> map);

    boolean deleteCommodity(int cid);
}
