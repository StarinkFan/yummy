package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Modification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModificationRepository  extends JpaRepository<Modification,String> {
    Modification findFirstByRid(int rid);
}
