package com.springboot.yummy.controller;

import com.springboot.yummy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired  //自动装配
    public OrderController(OrderService orderService) {
        this.orderService=orderService;
    }

    @RequestMapping(value = "/place", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean place(@RequestBody Map<String, Object> requestMap){
        return orderService.placeOrder(requestMap);
    }
}
