package com.springboot.yummy.entity;

import javax.persistence.*;

@Entity
@Table(name = "packageItems")
public class PackageItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int piid;
    private int pid;
    private int cid;
    private int num;
    private String name;
    private double price;

    public PackageItem(int pid, int cid, int num, String name, double price) {
        this.pid = pid;
        this.cid = cid;
        this.num=num;
        this.name = name;
        this.price = price;
    }

    public PackageItem(int piid, int pid, int cid, int num, String name, double price) {
        this.piid = piid;
        this.pid = pid;
        this.cid = cid;
        this.name = name;
        this.price = price;
    }

    public PackageItem(){}

    public int getPiid() {
        return piid;
    }

    public void setPiid(int piid) {
        this.piid = piid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
