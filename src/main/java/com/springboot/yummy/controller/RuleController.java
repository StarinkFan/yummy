package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Rule;
import com.springboot.yummy.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Transactional
@RestController
@RequestMapping("/rule")
public class RuleController {
    private final RuleService ruleService;

    @Autowired
    public RuleController(RuleService ruleService){
        this.ruleService=ruleService;
    }

    @RequestMapping(value = "/getRules", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<Rule> getRules(){
       return ruleService.getRules();
    }

    @RequestMapping(value = "/addRule", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public int addRule(@RequestBody Map<String, Object> requestMap){
        try{
            int percent= Integer.parseInt((String)requestMap.get("percent"));
            int top= Integer.parseInt((String)requestMap.get("top"));
            return ruleService.addRule(top, percent);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    @RequestMapping(value = "/deleteRule", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean deleteRule(@RequestBody Map<String, Object> requestMap){
        int ruid= (Integer)requestMap.get("ruid");
        return ruleService.deleteRule(ruid);
    }

    @RequestMapping(value = "/getCommonRule", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public int getCommonRule(){
        return ruleService.getCommonRule().getPercent();
    }

    @RequestMapping(value = "/saveCommonRule", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean saveCommonRule(@RequestBody Map<String, Object> requestMap){
        int percent= Integer.parseInt((String)requestMap.get("percent"));
        return ruleService.saveCommonRule(percent);
    }

}
