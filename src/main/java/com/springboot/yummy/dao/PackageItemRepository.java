package com.springboot.yummy.dao;

import com.springboot.yummy.entity.PackageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PackageItemRepository  extends JpaRepository<PackageItem, String> {
    List<PackageItem> findByPid(int pid);

    void deleteAllByPid(int pid);
}
