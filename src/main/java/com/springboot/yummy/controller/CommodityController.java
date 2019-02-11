package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    private final CommodityService commodityService;

    @Autowired  //自动装配
    public CommodityController(CommodityService commodityService) {
        this.commodityService=commodityService;
    }

    @RequestMapping(value = "/getCommodities", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Commodity[] getCommodities(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        return commodityService.getCommodities(rid);
    }

    @RequestMapping(value = "/saveCommodities", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean saveCommodities(@RequestBody Map<String, Object> requestMap){
        return commodityService.saveCommodities(requestMap);
    }


}
