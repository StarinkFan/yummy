package com.springboot.yummy.util;

import com.springboot.yummy.service.Impl.OrderServiceImpl;
import com.springboot.yummy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

@Component
public class UnpaidOrdersMonitor {
    static List<Integer> unpaidOrders = new ArrayList<>();
    @Autowired
    private OrderService orderService;
    private static UnpaidOrdersMonitor unpaidOrdersMonitor;

    @PostConstruct
    public void init() {
        unpaidOrdersMonitor = this;
        unpaidOrdersMonitor.orderService=this.orderService;

    }

    public static void addUnpaidOrder(int oid){
        System.out.println("订单"+oid+"待支付");
        unpaidOrders.add(oid);
        Timer timer = new Timer();
        TimeTask2 task=new TimeTask2(oid);
        timer.schedule(task, 120 * 1000);
    }

    public static boolean removeUnpaidOrder(int oid){
        System.out.println("订单"+oid+"已支付");
        return unpaidOrders.remove((Integer)oid);
    }

    static void deleteOrder(int oid){
        unpaidOrdersMonitor.orderService.deleteOrder(oid);
    }

}
