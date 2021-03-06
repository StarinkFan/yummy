package com.springboot.yummy.dao;

import com.springboot.yummy.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RuleRepository extends JpaRepository<Rule,String> {
    Rule findFirstByRuid(int ruid);
    List<Rule> findByTopGreaterThanOrderByTop(int min);
}
