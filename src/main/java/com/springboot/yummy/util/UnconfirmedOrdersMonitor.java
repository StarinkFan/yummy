package com.springboot.yummy.util;

import com.springboot.yummy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


@Component
public class UnconfirmedOrdersMonitor {
    static List<Integer> unconfirmedOrders = new ArrayList<>();
    @Autowired
    private OrderService orderService;
    private static UnconfirmedOrdersMonitor unconfirmedOrdersMonitor;

    @PostConstruct
    public void init() {
        unconfirmedOrdersMonitor = this;
        unconfirmedOrdersMonitor.orderService=this.orderService;

    }

    public static void addUnconfirmedOrder(int oid){
        System.out.println("订单"+oid+"待确认送达");
        unconfirmedOrders.add(oid);
        Timer timer = new Timer();
        TimeTask3 task=new TimeTask3(oid);
        timer.schedule(task, 60*60* 1000);
    }

    public static boolean removeUnconfirmedOrder(int oid){
        System.out.println("订单"+oid+"确认送达");
        return unconfirmedOrders.remove((Integer)oid);
    }

    static void confirmOrder(int oid){
        unconfirmedOrdersMonitor.orderService.setState(oid, 2, 0);
    }

}

