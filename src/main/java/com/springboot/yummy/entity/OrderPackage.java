package com.springboot.yummy.entity;

import javax.persistence.*;

@Entity
@Table(name = "orderPackages")
public class OrderPackage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int opid;
    private int oid;
    private int pid;
    private int num;
    private String name;
    private double price;

    public OrderPackage(){}

    public OrderPackage(int oid, int pid, int num, String name, double price) {
        this.oid = oid;
        this.pid = pid;
        this.num=num;
        this.name = name;
        this.price = price;
    }

    public OrderPackage(int opid, int oid, int pid, int num, String name, double price) {
        this.opid=opid;
        this.oid = oid;
        this.pid = pid;
        this.num=num;
        this.name = name;
        this.price = price;
    }

    public int getOpid() {
        return opid;
    }

    public void setOpid(int opid) {
        this.opid = opid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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
}
