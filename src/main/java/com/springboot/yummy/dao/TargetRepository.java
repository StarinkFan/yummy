package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TargetRepository extends JpaRepository<Target,String> {
    List<Target> findByUid(int uid);
}
