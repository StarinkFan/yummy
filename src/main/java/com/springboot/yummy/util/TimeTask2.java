package com.springboot.yummy.util;

import com.springboot.yummy.dao.*;
import com.springboot.yummy.entity.*;
import com.springboot.yummy.service.Impl.OrderServiceImpl;
import com.springboot.yummy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

class TimeTask2 extends TimerTask {

    private int oid;

    TimeTask2(int oid){
        this.oid=oid;
    }


    public void run() {
        int index=UnpaidOrdersMonitor.unpaidOrders.indexOf(oid);
        if(index>-1){
            UnpaidOrdersMonitor.deleteOrder(oid);
            System.out.println("订单"+oid+"超时未支付,已删除");
        }
    }

}