package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PackageRepository extends JpaRepository<Package, String> {
    List<Package> findByRid(int rid);
    Package findByPid(int pid);

}
