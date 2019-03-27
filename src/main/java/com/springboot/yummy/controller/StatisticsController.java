package com.springboot.yummy.controller;

import com.springboot.yummy.service.StatisticsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

}
