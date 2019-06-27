package com.springboot.yummy.dao;

import com.springboot.yummy.entity.PackageCart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author:Wang Mo
 * @Description：
 */
public interface PackageCartRepository  extends JpaRepository<PackageCart,String> {
    PackageCart findByPidAndRidAndUid(int pid,int rid,int uid);
}
