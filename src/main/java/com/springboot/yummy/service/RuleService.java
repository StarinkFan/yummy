package com.springboot.yummy.service;

import com.springboot.yummy.entity.Rule;

import java.util.List;

public interface RuleService {
    List<Rule> getRules();
    int addRule(int top, int percent);
    boolean saveCommonRule(int percent);
    boolean deleteRule(int ruid);
    Rule getCommonRule();
}
