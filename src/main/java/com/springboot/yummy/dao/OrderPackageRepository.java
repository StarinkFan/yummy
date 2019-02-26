package com.springboot.yummy.dao;

import com.springboot.yummy.entity.OrderPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderPackageRepository extends JpaRepository<OrderPackage, String> {
    List<OrderPackage> findByOid(int oid);
}
