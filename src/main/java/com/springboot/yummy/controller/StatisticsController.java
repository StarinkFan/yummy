package com.springboot.yummy.controller;

import com.springboot.yummy.service.StatisticsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService){
        this.statisticsService=statisticsService;
    }

    @RequestMapping(value = "/getSystemCondition", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> getSystemCondition(){
        return statisticsService.getSystemCondition();
    }

    @RequestMapping(value = "/getPersonalCondition", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> getPersonalCondition(@RequestBody Map<String, Object> requestMap){
        int uid= Integer.parseInt((String)requestMap.get("uid"));
        return statisticsService.getPersonalCondition(uid);
    }
}
