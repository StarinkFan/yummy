package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Order;
import com.springboot.yummy.entity.OrderDetail;
import com.springboot.yummy.service.GainService;
import com.springboot.yummy.service.Impl.GainServiceImpl;
import com.springboot.yummy.service.OrderService;
import com.springboot.yummy.util.UnconfirmedOrdersMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Transactional
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final GainService gainService;

    @Autowired  //自动装配
    public OrderController(OrderService orderService, GainService gainService) {
        this.orderService=orderService;
        this.gainService=gainService;
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
        if(result>0){
            gainService.addGain(oid);
        }
        return result;
    }

    @RequestMapping(value = "/arrive", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean arrive(@RequestBody Map<String, Object> requestMap){
        int oid= (Integer)requestMap.get("oid");
        int result=orderService.setState(oid,2, 0);
        if(result>0){
            UnconfirmedOrdersMonitor.removeUnconfirmedOrder(oid);
            gainService.addGain(oid);
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping(value = "/getUserOrders", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<Order> getUserDetails(@RequestBody Map<String, Object> requestMap){
        int uid= Integer.parseInt((String)requestMap.get("uid"));
        return orderService.getUserOrders(uid);
    }

    @RequestMapping(value = "/getRestaurantOrders", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public List<Order> getRestaurantDetails(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        return orderService.getRestaurantOrders(rid);
    }
}
