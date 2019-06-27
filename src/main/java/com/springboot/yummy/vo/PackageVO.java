package com.springboot.yummy.vo;

import com.springboot.yummy.entity.PackageItem;

/**
 * @Author:Wang Mo
 * @Description：
 */
public class PackageVO {
     int pid;
     int rid;
     String name;
     int sold;
     double price;
     String description;
     String state;
     boolean ifValid;
     PackageItem[] items;
    int num;

    public PackageVO(int pid, int rid, String name, int sold, double price, String description, String state, boolean ifValid, PackageItem[] items, int num) {
        this.pid = pid;
        this.rid = rid;
        this.name = name;
        this.sold = sold;
        this.price = price;
        this.description = description;
        this.state = state;
        this.ifValid = ifValid;
        this.items = items;
        this.num = num;
    }

    public PackageVO() {
    }

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

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public PackageItem[] getItems() {
        return items;
    }

    public void setItems(PackageItem[] items) {
        this.items = items;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
