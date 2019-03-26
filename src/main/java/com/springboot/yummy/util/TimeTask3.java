package com.springboot.yummy.util;

import java.util.TimerTask;

class TimeTask3 extends TimerTask {

    private int oid;

    TimeTask3(int oid){
        this.oid=oid;
    }


    public void run() {
        int index=UnconfirmedOrdersMonitor.unconfirmedOrders.indexOf(oid);
        if(index>-1){
            UnconfirmedOrdersMonitor.unconfirmedOrders.remove((Integer)oid);
            UnconfirmedOrdersMonitor.confirmOrder(oid);
            System.out.println("订单"+oid+"超时未确认送达，已自动确认");
        }
    }

}