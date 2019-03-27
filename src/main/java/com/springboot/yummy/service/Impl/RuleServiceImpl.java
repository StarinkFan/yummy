package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.RuleRepository;
import com.springboot.yummy.entity.Rule;
import com.springboot.yummy.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RuleService")
public class RuleServiceImpl implements RuleService {
    @Autowired
    RuleRepository ruleRepository;

    @Override
    public List<Rule> getRules() {
        List<Rule> rules=ruleRepository.findAll();
        List<Rule> result=new ArrayList<>();
        for(Rule rule: rules){
            if(rule.getTop()>=0){
                result.add(rule);
            }
        }
        return result;
    }

    @Override
    public int addRule(int top, int percent) {
        List<Rule> rules=ruleRepository.findAll();
        for(Rule rule:rules){
            if(rule.getTop()==top){
                return -1;
            }
        }
        ruleRepository.save(new Rule(top, percent));
        return 1;
    }

    @Override
    public boolean saveCommonRule(int percent) {
        try {
            Rule rule=ruleRepository.findFirstByRuid(1);
            rule.setPercent(percent);
            ruleRepository.save(rule);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteRule(int ruid) {
        try {
            Rule rule=ruleRepository.findFirstByRuid(ruid);
            ruleRepository.delete(rule);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Rule getCommonRule() {
        return ruleRepository.findFirstByRuid(1);
    }
}
