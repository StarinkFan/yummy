package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommodityRepository extends JpaRepository<Commodity,String> {
    List<Commodity> findByRid(int rid);
    void deleteByCid(int cid);
}
