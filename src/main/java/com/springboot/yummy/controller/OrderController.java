package com.springboot.yummy.controller;

import com.springboot.yummy.entity.OrderDetail;
import com.springboot.yummy.service.OrderService;
import com.springboot.yummy.util.UnconfirmedOrdersMonitor;
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
    public int place(@RequestBody Map<String, Object> requestMap){
        return orderService.placeOrder(requestMap);
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public OrderDetail getOrderDetail(@RequestBody Map<String, Object> requestMap){
        int oid= (Integer)requestMap.get("oid");
        return orderService.getOrderDetail(oid);
    }

    @RequestMapping(value = "/refund", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public int refund(@RequestBody Map<String, Object> requestMap){
        int oid= (Integer)requestMap.get("oid");
        int result=orderService.setState(oid,3, 0);
        return result;
    }

    @RequestMapping(value = "/arrive", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean arrive(@RequestBody Map<String, Object> requestMap){
        int oid= (Integer)requestMap.get("oid");
        int result=orderService.setState(oid,2, 0);
        if(result>0){
            UnconfirmedOrdersMonitor.removeUnconfirmedOrder(oid);
            return true;
        }else {
            return false;
        }
    }
}
