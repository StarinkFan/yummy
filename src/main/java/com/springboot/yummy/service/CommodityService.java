package com.springboot.yummy.service;

import com.springboot.yummy.entity.Commodity;

import java.util.Map;


public interface CommodityService {
    Commodity[] getCommodities(int rid);

    boolean saveCommodities(Map<String, Object> map);
}
