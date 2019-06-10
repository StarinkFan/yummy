package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Discount;
import com.springboot.yummy.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/discount")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired  //自动装配
    public DiscountController(DiscountService discountService) {
        this.discountService=discountService;
    }

    @RequestMapping(value = "/getDiscounts", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Discount[] getDiscounts(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        return discountService.getDiscounts(rid);
    }

    @RequestMapping(value = "/addDiscount", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public int addDiscount(@RequestBody Map<String, Object> requestMap){
        return discountService.saveDiscount(requestMap);
    }

    @RequestMapping(value = "/deleteDiscount", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean deleteDiscount(@RequestBody Map<String, Object> requestMap){
        int did= (Integer)requestMap.get("did");
        return discountService.deleteDiscount(did);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean validate(@RequestBody Map<String, Object> requestMap){
        int did= (Integer)requestMap.get("did");
        return discountService.validate(did);
    }

    @RequestMapping(value = "/invalidate", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean invalidate(@RequestBody Map<String, Object> requestMap){
        int did= (Integer)requestMap.get("did");
        return discountService.invalidate(did);
    }

    @RequestMapping(value = "/hasSameName", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean hasSameName(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        int did= (Integer)requestMap.get("did");
        int total = Integer.parseInt((String)requestMap.get("total"));
        return discountService.hasSameDiscount(rid, did, total);
    }
}
