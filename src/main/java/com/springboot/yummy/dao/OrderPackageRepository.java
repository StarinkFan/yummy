package com.springboot.yummy.dao;

import com.springboot.yummy.entity.OrderPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPackageRepository extends JpaRepository<OrderPackage, String> {
}
