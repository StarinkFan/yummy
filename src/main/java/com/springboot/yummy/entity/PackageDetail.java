package com.springboot.yummy.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PackageDetail {
    private int pid;
    private int rid;
    private String name;
    private double price;
    private LocalDate beginDate;
    private LocalDate endDate;
    private PackageItem[] items;

    public PackageDetail(int rid, String name, double price, LocalDate beginDate, LocalDate endDate, PackageItem[] items) {
        this.rid = rid;
        this.name = name;
        this.price = price;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.items=items;
    }

    public PackageDetail(int pid, int rid, String name, double price, LocalDate beginDate, LocalDate endDate, PackageItem[] items) {
        this.pid=pid;
        this.rid = rid;
        this.name = name;
        this.price = price;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.items=items;
    }

    public PackageDetail(){}

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public PackageItem[] getItems() {
        return items;
    }

    public void setItems(PackageItem[] items) {
        this.items = items;
    }
}
