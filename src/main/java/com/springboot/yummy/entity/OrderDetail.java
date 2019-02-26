package com.springboot.yummy.entity;

import java.util.List;

public class OrderDetail {
    private Order orderInfo;
    private List<OrderCommodity> commodities;
    private List<PackageDetail> packages;

    public OrderDetail(){}

    public OrderDetail(Order orderInfo, List<OrderCommodity> commodities, List<PackageDetail> packages){
        this.orderInfo=orderInfo;
        this.commodities=commodities;
        this.packages=packages;
    }

    public Order getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Order orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<OrderCommodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<OrderCommodity> commodities) {
        this.commodities = commodities;
    }

    public List<PackageDetail> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageDetail> packages) {
        this.packages = packages;
    }
}
