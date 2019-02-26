package com.springboot.yummy.entity;

public class OrderDetail {
    private Order orderInfo;
    private OrderCommodity[] commodities;
    private PackageDetail[] packages;

    public OrderDetail(){}

    public OrderDetail(Order orderInfo, OrderCommodity[] commodities, PackageDetail[] packages){
        this.orderInfo=orderInfo;
        this.commodities=commodities;
        this.packages=packages;
    }

    public OrderCommodity[] getCommodities() {
        return commodities;
    }

    public void setCommodities(OrderCommodity[] commodities) {
        this.commodities = commodities;
    }

    public PackageDetail[] getPackages() {
        return packages;
    }

    public void setPackages(PackageDetail[] packages) {
        this.packages = packages;
    }

    public Order getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Order orderInfo) {
        this.orderInfo = orderInfo;
    }
}
