package com.springboot.yummy.dao;

import com.springboot.yummy.entity.CommodityCart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:Wang Mo
 * @Description：
 */
public interface CommodityCartRepository  extends JpaRepository<CommodityCart,String> {
    CommodityCart findByCidAndRidAndUid(int cid,int rid,int uid);
}
