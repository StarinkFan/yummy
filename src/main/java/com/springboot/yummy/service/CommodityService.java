package com.springboot.yummy.service;

import com.springboot.yummy.entity.Commodity;

import java.util.Map;


public interface CommodityService {
    Commodity[] getCommodities(int rid);

    int saveCommodity(Map<String, Object> map);

    int deleteCommodity(int cid);

    boolean validate(int cid);

    boolean invalidate(int cid);

    boolean hasSameName(int rid, int cid, String  name);
}
