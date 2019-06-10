package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount,String> {
    List<Discount> findByRid(int rid);

    Discount findFirstByDid(int did);

    void deleteByDid(int did);
}
